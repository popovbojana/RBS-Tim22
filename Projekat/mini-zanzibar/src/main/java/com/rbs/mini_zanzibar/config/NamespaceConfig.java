package com.rbs.mini_zanzibar.config;

import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "namespace")
public class NamespaceConfig {
    private String namespace;
    private Map<String, Map<String, Object>> relations;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Map<String, Map<String, Object>> getRelations() {
        return relations;
    }

    public void setRelations(Map<String, Map<String, Object>> relations) {
        this.relations = relations;
    }

}
