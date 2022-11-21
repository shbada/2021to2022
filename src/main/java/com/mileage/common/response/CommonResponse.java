package com.mileage.common.response;

import org.springframework.http.HttpStatus;

public class CommonResponse {
    public static <T> ResponseDto<T> success(T items) {
        ItemDto<T> itemDto = ItemDto.<T>builder()
                .items(items)
                .build();

        return new ResponseDto<>(itemDto);
    }

    public static <T> ResponseDto<T> success() {
        return CommonResponse.success(null);
    }

    public static <T> ResponseDto<T> fail(HttpStatus status, String errorCode, String errorMessage) {
        ItemDto<T> itemDto = ItemDto.<T>builder()
                .code(errorCode)
                .message(errorMessage)
                .build();

        return new ResponseDto<>(status.value(), status.getReasonPhrase(), itemDto);
    }

    public static <T> ResponseDto<T> fail(ErrorCode errorCode) {
        return CommonResponse.fail(errorCode.getHttpStatus(), errorCode.getCode(), errorCode.getMessage());
    }
}
