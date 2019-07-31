package com.gipl.track32.app.api;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by naresh on 19-Jul-2019.
 */
// T is used in extending classes
public class ApiResponse<T> {

    public static <T> ApiErrorResponse<T> create(Throwable throwable) {
        String errorMsg = throwable.getMessage() != null ? throwable.getMessage() : "unknown error";
        return new ApiErrorResponse<>(errorMsg);
    }

    public static <T> ApiResponse<T> create(Response<T> response) {

        if (response.isSuccessful()) {
            T body = response.body();
            if (body == null || response.code() == 204) {
                return new ApiEmptyResponse<>();
            }

            return new ApiSuccessResponse<>(body);
        } else {
            String errorMsg = "";
            try {
                errorMsg = response.errorBody() != null ? response.errorBody().string() : "unknown error";
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (errorMsg.isEmpty()) {
                errorMsg = response.message();
            }
            return new ApiErrorResponse<>(errorMsg);
        }
    }

}



