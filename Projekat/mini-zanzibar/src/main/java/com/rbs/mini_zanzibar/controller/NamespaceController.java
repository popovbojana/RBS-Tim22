package com.rbs.mini_zanzibar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbs.mini_zanzibar.config.NamespaceConfig;
import com.rbs.mini_zanzibar.service.impl.ConsulDBImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/namespace")
public class NamespaceController {
    @Autowired
    private ConsulDBImpl consulDB;
    @Autowired
    private ObjectMapper mapper;

    @PostMapping(value = "/{namespace}", consumes = "application/json")
    public ResponseEntity<?> setConfig(@PathVariable String namespace,
                                       @RequestBody NamespaceConfig config){
        try{
            consulDB.setKV(namespace,
                           mapper.writeValueAsString(config.getRelations()));
            return ResponseEntity.ok().build();
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{namespace}")
    public ResponseEntity<?> getConfig(@PathVariable String namespace) {
       String config = consulDB.getKV(namespace);
       return  ResponseEntity.ok(config);
    }
}
