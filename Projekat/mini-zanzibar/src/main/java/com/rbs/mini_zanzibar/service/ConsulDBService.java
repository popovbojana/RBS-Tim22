package com.rbs.mini_zanzibar.service;

public interface ConsulDBService {

    boolean setKV(String key, String value);

    String getKV(String key);

}



