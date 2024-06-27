package com.rbs.mini_zanzibar.service.impl;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.rbs.mini_zanzibar.exception.NotFoundException;
import com.rbs.mini_zanzibar.service.ConsulDBService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConsulDBServiceImpl implements ConsulDBService {

    private final ConsulClient client;

    private final String version;

    public ConsulDBServiceImpl(@Value("${spring.cloud.consul.host}") String host,
                               @Value("${spring.cloud.consul.port}") int port,
                               @Value("${consul.version}") String version) {
        this.client = new ConsulClient(host, port);
        this.version = version;
    }

    @Override
    public boolean setKV(String key, String value) {
        return client.setKVValue(key + "v" + version, value).getValue();
    }

    @Override
    public String getKV(String key) {
        GetValue value = client.getKVValue(key + "v" + version).getValue();
        if (value != null){
            return value.getDecodedValue();
        } else {
            throw new NotFoundException(String.format("No namespace found with key: %s", key));
        }
    }

}
