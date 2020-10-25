package com.geek.springboot;

public class CanNotFreezeException extends RuntimeException{
    public CanNotFreezeException() {
    }

    public CanNotFreezeException(String message) {
        super(message);
    }
}
