package com.staxter.springrest.user;

public abstract class UserException extends RuntimeException {
    private final String code;

    public UserException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
