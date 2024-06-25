package com.rbs.mini_zanzibar.service.impl;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.rbs.mini_zanzibar.service.ConsulDBService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConsulDBImpl implements ConsulDBService {

    private final ConsulClient client;

    public ConsulDBImpl(@Value("${spring.cloud.consul.host}") String host,
                        @Value("${spring.cloud.consul.port}") int port) {
        this.client = new ConsulClient(host, port);
    }
    @Override
    public boolean setKV(String key, String value) {
        return client.setKVValue(key, value).getValue();
    }

    @Override
    public String getKV(String key) {
        GetValue value = client.getKVValue(key).getValue();
        return value != null ? value.getDecodedValue() : null;
    }
}
