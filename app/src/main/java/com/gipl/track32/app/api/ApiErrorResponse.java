package com.gipl.track32.app.api;

/**
 * Created by naresh on 19-Jul-2019.
 */
public class ApiErrorResponse<T> extends ApiResponse<T> {

    private String errorMessage;

    ApiErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}