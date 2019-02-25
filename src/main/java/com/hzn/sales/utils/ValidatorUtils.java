package com.hzn.sales.utils;

import org.apache.commons.collections.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class ValidatorUtils {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> ValidatorResult validate(T obj) {
        ValidatorResult result = new ValidatorResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            result.setError(true);
            Map<String, String> errorMsg = new HashMap<String, String>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
            }
            result.setErrorMsg(convertRrrorMsg(errorMsg));
        }
        return result;
    }

    public static <T> ValidatorResult validateProperty(T obj, String propertyName) {
        ValidatorResult result = new ValidatorResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            result.setError(true);
            Map<String, String> errorMsg = new HashMap<String, String>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(propertyName, cv.getMessage());
            }
            result.setErrorMsg(convertRrrorMsg(errorMsg));
        }
        return result;
    }

    public static <T> ValidatorResult validateProperty(T obj, String... propertyNames) {
        ValidatorResult result = new ValidatorResult();
        Map<String, String> errorMsg = new HashMap<String, String>();
        for (String propertyName : propertyNames) {
            Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
            if (CollectionUtils.isNotEmpty(set)) {
                result.setError(true);
                for (ConstraintViolation<T> cv : set) {
                    errorMsg.put(propertyName, cv.getMessage());
                }
            }
        }
        result.setErrorMsg(convertRrrorMsg(errorMsg));
        return result;
    }

    public static class ValidatorResult {

        private boolean isError;

        private String errorMsg;

        public boolean isError() {
            return isError;
        }

        public void setError(boolean error) {
            isError = error;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }


    }

    public static String convertRrrorMsg(Map<String, String> errorMsg) {
        StringBuffer strb = new StringBuffer("");
        for (Map.Entry<String,String> entry:errorMsg.entrySet()
             ) {
            strb.append(entry.getValue()).append(",");
        }
//        errorMsg.values().forEach(value -> {
//            strb.append(value).append(",");
//        });
        return strb.toString();
    }
}