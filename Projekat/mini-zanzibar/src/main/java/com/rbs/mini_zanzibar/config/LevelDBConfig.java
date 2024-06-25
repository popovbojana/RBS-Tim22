package com.rbs.mini_zanzibar.config;

import com.rbs.mini_zanzibar.exception.LevelDBInitializationException;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.fusesource.leveldbjni.JniDBFactory.factory;

@Configuration
public class LevelDBConfig {

    private static final Logger logger = Logger.getLogger(LevelDBConfig.class.getName());

    @Value("${leveldb.path}")
    private String dbPath;

    @Bean
    public DB levelDb() {
        Options options = new Options();
        options.createIfMissing(true);
        DB db;

        try {
            db = factory.open(Paths.get(dbPath).toFile(), options);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to open LevelDB database at path: " + dbPath, e);
            throw new LevelDBInitializationException("Could not initialize LevelDB at path: " + dbPath, e);
        }

        return db;
    }
}
