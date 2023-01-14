package com.sample.api.exception;

import com.sample.api.enums.EnumMessage;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private final String enumMessage;
    private int enumCode = 0;
    private List<ValidationItem> items;

    public BadRequestException() {
        super(EnumMessage.HTTP_BAD_REQUEST.getMessage());
        this.enumMessage = EnumMessage.HTTP_BAD_REQUEST.getMessage();
    }

    public BadRequestException(EnumMessage enumMessage) {
        super(enumMessage.getMessage());
        this.enumMessage = enumMessage.getMessage();
    }

    public BadRequestException(String message) {
        super(message);
        this.enumMessage = message;
    }

    /**
     * code, message 지정
     * @param code
     * @param message
     */
    public BadRequestException(int code, String message) {
        super(message);
        this.enumMessage = message;
        this.enumCode = code;
    }

    /**
     * validation error
     * @param items
     */
    public BadRequestException( List<ValidationItem> items) {
        super(EnumMessage.VALID_PARAMETER.getMessage());
        this.enumMessage = EnumMessage.VALID_PARAMETER.getMessage();

        this.items = items;
    }

    public int getEnumCode() {
        return enumCode;
    }

    public List<ValidationItem> getItems() {
        return items;
    }
}
