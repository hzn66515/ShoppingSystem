package com.hzn.sales.utils;

import java.io.Serializable;

public class GeneralResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private T value;
    public GeneralResponse(){
        value=null;
        code=Code.SUCCESS;
        message="操作成功";
    }
    public static class Code{
        private Code(){}
        public static final int SUCCESS=0;
        public static final int FAIL=1;
    }

    public int getCode() {
        return code;
    }

    public GeneralResponse<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public GeneralResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getValue() {
        return value;
    }

    public GeneralResponse<T> setValue(T value) {
        this.value = value;
        return this;
    }
}

