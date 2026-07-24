package com.rino.pethealthapp.dto.response;

public class ValidationErrorResponse {

    // エラーになったフィールド名
    private String field;

    // エラーメッセージ
    private String message;

    // =================================
    // コンストラクタ
    public ValidationErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }

    // getter
    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}
