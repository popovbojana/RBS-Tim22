package com.rbs.mini_zanzibar.config;

import java.util.List;
import java.util.Map;



public class NamespaceConfig {
    private String namespace;
    private Map<String, List<String>> relations;


    public NamespaceConfig(){};
    public NamespaceConfig(String namespace, Map<String, List<String>> relations) {
        this.namespace = namespace;
        this.relations = relations;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Map<String, List<String>> getRelations() {
        return relations;
    }

    public void setRelations(Map<String, List<String>> relations) {
        this.relations = relations;
    }

}
