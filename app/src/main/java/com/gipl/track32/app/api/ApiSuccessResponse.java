package com.gipl.track32.app.api;

/**
 * Created by naresh on 19-Jul-2019.
 */

public class ApiSuccessResponse<T> extends ApiResponse<T> {

    private T body;

    ApiSuccessResponse(T body) {
        this.body = body;
    }

    public T getBody() {
        return body;
    }
}