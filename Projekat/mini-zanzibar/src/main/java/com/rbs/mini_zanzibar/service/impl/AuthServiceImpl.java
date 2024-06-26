package com.rbs.mini_zanzibar.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbs.mini_zanzibar.dto.AuthDTO;
import com.rbs.mini_zanzibar.dto.ResponseDTO;
import com.rbs.mini_zanzibar.exception.BadRequestException;
import com.rbs.mini_zanzibar.service.AuthService;
import com.rbs.mini_zanzibar.service.LevelDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final LevelDBService levelDBService;

    private final ConsulDBServiceImpl consulDBService;

    private static final Pattern OBJECT_PATTERN = Pattern.compile("^[a-zA-Z0-9]+:[a-zA-Z0-9]+$");

    private final ObjectMapper objectMapper;

    @Override
    public void saveAcl(AuthDTO authDTO) {
        // Find namespace
        String[] objectParts = authDTO.getObject().split(":");
        String namespace = objectParts[0];
        String namespaceConfigString = consulDBService.getKV(namespace);

        // Parse namespace configuration
        Map<String, List<String>> namespaceConfig;
        try {
            namespaceConfig = objectMapper.readValue(namespaceConfigString, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new BadRequestException("Error while parsing namespace configuration.");
        }

        // Check if relation exists in namespace configuration
        if (!namespaceConfig.containsKey(authDTO.getRelation())) {
            throw new BadRequestException(String.format("Relation %s doesn't exist in namespace %s.", authDTO.getRelation(), namespace));
        }

        levelDBService.save(String.format("%s@%s", authDTO.getObject(), authDTO.getUser()), authDTO.getRelation());
    }

    @Override
    public ResponseDTO findAcl(String object, String relation, String user) {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .authorized(false)
                .build();

        // Object format check
        if (!OBJECT_PATTERN.matcher(object).matches()) {
            throw new BadRequestException("Object must be in format 'namespace:document'.");
        }

        // Find namespace
        String[] objectParts = object.split(":");
        String namespace = objectParts[0];
        String namespaceConfigString = consulDBService.getKV(namespace);

        // Parse namespace configuration
        Map<String, List<String>> namespaceConfig;
        try {
            namespaceConfig = objectMapper.readValue(namespaceConfigString, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new BadRequestException("Error while parsing namespace configuration.", e);
        }

        // Check if relation exists in namespace configuration
        if (!namespaceConfig.containsKey(relation)) {
            throw new BadRequestException(String.format("Relation %s doesn't exist in namespace %s.", relation, namespace));
        }

        // Find real relation
        String key = String.format("%s@%s", object, user);
        String foundRelation = levelDBService.get(key);
        if (foundRelation == null) {
            return responseDTO;
        }

        // Check relation
        if (checkRelation(relation, foundRelation, namespaceConfig)) {
            responseDTO.setAuthorized(true);
        }

        return responseDTO;
    }

    private boolean checkRelation(String relation, String foundRelation, Map<String, List<String>> namespaceConfig) {
        // Check for exact relation
        if (relation.equals(foundRelation)) {
            System.out.println("found " + foundRelation);
            return true;
        }

        // Check for parent relations
        List<String> parentRelations = namespaceConfig.get(foundRelation);
        if (parentRelations != null) {
            for (String parentRelation : parentRelations) {
                System.out.println("parentRelation " + parentRelation);
                if (checkRelation(relation, parentRelation, namespaceConfig)) {
                    return true;
                }
            }
        }

        return false;
    }

}
