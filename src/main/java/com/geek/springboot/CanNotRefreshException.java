package com.geek.springboot;

public class CanNotRefreshException extends RuntimeException{
    public CanNotRefreshException() {
    }

    public CanNotRefreshException(String message) {
        super(message);
    }
}
