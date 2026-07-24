package com.rino.pethealthapp.dto.response;

import java.util.List;

public class ErrorResponse {

    // バリデーションエラー一覧
    private List<ValidationErrorResponse> errors;

    // =================================
    // コンストラクタ
    public ErrorResponse(List<ValidationErrorResponse> errors) {
        this.errors = errors;
    }

    // getter
    public List<ValidationErrorResponse> getErrors() {
        return errors;
    }
}
