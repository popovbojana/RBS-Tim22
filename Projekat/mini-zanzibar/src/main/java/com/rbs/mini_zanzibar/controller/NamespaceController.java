package com.rbs.mini_zanzibar.controller;

import com.rbs.mini_zanzibar.config.NamespaceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/namespace")
public class NamespaceController {

    @Autowired
    private NamespaceConfig namespaceConfig;

    @PostMapping(value = "/namespace/{namespace}", consumes = "application/json")
    public ResponseEntity<?> setConfig(@PathVariable String namespace,
                                           @RequestBody NamespaceConfig namespaceConfig){
        //namespaceConfig.setConfig(namespace, objectMapper.writeValueAsString(config.getRelations()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/namespace/{namespace}")
    public ResponseEntity<?> getConfig(@PathVariable String namespace) {
       //namespaceConfig.getConfig(namespace);
       return  ResponseEntity.ok().build();
    }
}
