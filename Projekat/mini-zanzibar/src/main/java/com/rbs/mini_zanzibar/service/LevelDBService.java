package com.rbs.mini_zanzibar.service;

public interface LevelDBService {

    void save(String key, String value);

    String get(String key);

    void update(String key, String value);

    void delete(String key);

    boolean checkIfExists(String key);

}
