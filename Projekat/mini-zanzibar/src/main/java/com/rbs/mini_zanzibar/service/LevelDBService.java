package com.rbs.mini_zanzibar.service;

public interface LevelDBService {

    void save(String key, String value);

    String get(String key);

}
