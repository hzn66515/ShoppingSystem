package com.hzn.sales.utils;

public final class RespFactory {
    private RespFactory() {
    }

    public static <T> GeneralResponse<T> buildFail(String message) {
        return new GeneralResponse<T>().setCode(GeneralResponse.Code.FAIL).setMessage(message);
    }

    public static <T> GeneralResponse<T> buildSuc(T value) {
        return new GeneralResponse<T>().setValue(value);
    }
}
