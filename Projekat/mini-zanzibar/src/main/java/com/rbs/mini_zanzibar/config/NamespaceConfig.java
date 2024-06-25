package com.rbs.mini_zanzibar.config;

import java.util.List;
import java.util.Map;



public class NamespaceConfig {
    private Map<String, List<String>> relations;


    public NamespaceConfig(){};
    public NamespaceConfig(Map<String, List<String>> relations) {
        this.relations = relations;
    }

    public Map<String, List<String>> getRelations() {
        return relations;
    }

    public void setRelations(Map<String, List<String>> relations) {
        this.relations = relations;
    }

}
