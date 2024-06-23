package com.rbs.mini_zanzibar.service.impl;

import com.rbs.mini_zanzibar.exception.NotFoundException;
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
        if (valueBytes != null) {
            return new String(valueBytes, StandardCharsets.UTF_8);
        } else {
            throw new NotFoundException(String.format("No data found with key %s.", key));
        }
    }

    @Override
    public void update(String key, String value) {
        save(key, value);
    }

    @Override
    public void delete(String key) {
        levelDB.delete(key.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public boolean checkIfExists(String key) {
        try {
            get(key);
            return true;
        } catch (NotFoundException ex) {
            return false;
        }
    }

}
