package com.rbs.mini_zanzibar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbs.mini_zanzibar.config.NamespaceConfig;
import com.rbs.mini_zanzibar.service.impl.ConsulDBImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            return new ResponseEntity<>(String.format("Successfully saved namespace: %s:", namespace), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Bad json format", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{namespace}")
    public ResponseEntity<?> getConfig(@PathVariable String namespace) {
       String config = consulDB.getKV(namespace);
       if(config != null){
           return new ResponseEntity<>(config, HttpStatus.OK);
       }else{
           return new ResponseEntity<>("Namespace not found!", HttpStatus.NOT_FOUND);
       }
    }
}
