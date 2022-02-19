package com.example.studyorder.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private Result result;
    private T data;
    private String message;
    private String errorCode;

    public static <T> CommonResponse<T> success(T data,String message){
        return (CommonResponse<T>) CommonResponse.builder()
            .result(Result.SUCCESS)
            .message(message)
            .data(data)
            .build();
    }

    public static <T> CommonResponse<T> success(T data){
        return CommonResponse.success(data, null);
    }

    public static <T> CommonResponse<T> fail(T data,String errorCode){
        return (CommonResponse<T>) CommonResponse.builder()
            .result(Result.SUCCESS)
            .errorCode(errorCode)
            .data(data)
            .build();
    }

    public static CommonResponse fail(ErrorCode errorCode) {
        return CommonResponse.builder()
            .result(Result.FAIL)
            .message(errorCode.getErrorMsg())
            .errorCode(errorCode.name())
            .build();
    }

    public enum Result {
        SUCCESS, FAIL
    }
}
