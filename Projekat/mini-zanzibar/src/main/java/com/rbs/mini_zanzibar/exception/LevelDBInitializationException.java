package com.rbs.mini_zanzibar.exception;

public class LevelDBInitializationException extends AbstractException {

    public LevelDBInitializationException(String message) {
        super(message);
    }

    public LevelDBInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LevelDBInitializationException(Throwable cause) {
        super(cause);
    }

}