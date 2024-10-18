package com.demosql.model.response;

public class ApiResponse<T> {
    private int code;
    private T data;
    private String message;
    private boolean success;

    public ApiResponse(int code, boolean success, String message, T data) {
        this.data = data;
        this.message = message;
        this.success = success;
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }
}

