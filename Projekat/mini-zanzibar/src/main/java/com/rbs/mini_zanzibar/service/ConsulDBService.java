package com.rbs.mini_zanzibar.service;

public interface ConsulDBService {

    public boolean setKV(String key, String value);

    public String getKV(String key);
}



