package com.rbs.mini_zanzibar.controller;

import com.rbs.mini_zanzibar.service.LevelDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Only for test purposes, delete later
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/leveldb")
public class LevelDBController {

    private final LevelDBService levelDBService;

    @PostMapping
    public ResponseEntity<String> saveData(@RequestParam String key, @RequestParam String value) {
        levelDBService.save(key, value);
        return new ResponseEntity<>(String.format("Successfully saved data: %s:%s.", key, value), HttpStatus.OK);
    }

    @GetMapping("/{key}")
    public ResponseEntity<String> getData(@PathVariable String key) {
        return new ResponseEntity<>(String.format("Found data: %s:%s.", key, levelDBService.get(key)), HttpStatus.OK);
    }

    @PutMapping("/{key}")
    public ResponseEntity<String> updateData(@PathVariable String key, @RequestParam String value) {
        levelDBService.update(key, value);
        return new ResponseEntity<>(String.format("Successfully updated data with key %s to value %s", key, value), HttpStatus.OK);
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<String> deleteData(@PathVariable String key) {
        levelDBService.delete(key);
        return new ResponseEntity<>(String.format("Successfully deleted data with key %s", key), HttpStatus.OK);
    }

    @GetMapping("/{key}/exists")
    public ResponseEntity<String> checkIfExists(@PathVariable String key) {
        return new ResponseEntity<>(levelDBService.checkIfExists(key) ? String.format("Data with key %s exist!", key) : String.format("Data with key %s doesn't exist!", key), HttpStatus.OK);
    }

}
