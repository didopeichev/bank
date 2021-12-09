package com.tarya.bank.model;

public class ApiError {
    public enum CodeEnum {

        INCORRECT_VALIDATION("INCORRECT_VALIDATION"),

        EMPTY_RESPONSE("EMPTY_RESPONSE"),

        FORBIDDEN("FORBIDDEN"),

        INSUFFICIENT_FUNDS("INSUFFICIENT_FUNDS"),

        UNAUTHORIZED("UNAUTHORIZED");

        private final String value;

        CodeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static CodeEnum fromValue(String value) {
            for (CodeEnum b : CodeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    public ApiError() {
    }

    private CodeEnum code;

    private String detail;

    public ApiError(CodeEnum code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    public CodeEnum getCode() {
        return code;
    }

    public ApiError code(CodeEnum code) {
        this.code = code;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public ApiError detail(String detail) {
        this.detail = detail;
        return this;
    }
}
