package com.example.demo.exceptions;

public class DamagedSystemEmptyException extends RuntimeException {
    public DamagedSystemEmptyException(String message) {
        super(message);
    }
}