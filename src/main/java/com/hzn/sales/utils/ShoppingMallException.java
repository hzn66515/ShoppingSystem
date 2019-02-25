package com.hzn.sales.utils;

import org.apache.commons.lang3.StringUtils;

public class ShoppingMallException extends RuntimeException {

    private String errorMsg;


    public ShoppingMallException(String errorMsg, Throwable throwable) {
        this.errorMsg = errorMsg;
    }

    public static void checkArgument(boolean result, String errorMsg) throws ShoppingMallException {
        if (!result) {
            throw new ShoppingMallException(errorMsg, null);
        }
    }

    public String getMessage() {
        if (StringUtils.isNotEmpty(this.errorMsg)) {
            return this.errorMsg;
        }
        return "未知错误";
    }

}
