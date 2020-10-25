package com.geek.springboot;

public class NotTransferToSelfException extends RuntimeException {
    public NotTransferToSelfException(String message) {
        super(message);
    }

    public NotTransferToSelfException() {
    }
}
