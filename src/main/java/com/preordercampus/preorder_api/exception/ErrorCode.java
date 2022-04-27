package com.preordercampus.preorder_api.exception;

public enum ErrorCode {

    EMAIL_DUPLICATION(400, "M001", "Email is Duplication");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
