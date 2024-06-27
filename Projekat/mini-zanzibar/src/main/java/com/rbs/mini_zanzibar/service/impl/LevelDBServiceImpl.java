package com.rbs.mini_zanzibar.service.impl;

import com.rbs.mini_zanzibar.service.LevelDBService;
import lombok.RequiredArgsConstructor;
import org.iq80.leveldb.DB;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class LevelDBServiceImpl implements LevelDBService {

    private final DB levelDB;

    @Override
    public void save(String key, String value) {
        levelDB.put(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String get(String key) {
        byte[] valueBytes;
        valueBytes = levelDB.get(key.getBytes(StandardCharsets.UTF_8));
        return valueBytes != null ? new String(valueBytes, StandardCharsets.UTF_8) : null;
    }

}
