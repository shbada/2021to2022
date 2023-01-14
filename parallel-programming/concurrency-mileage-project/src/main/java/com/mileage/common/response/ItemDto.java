package com.mileage.common.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto<T> {
    private String code;

    private String message;

    private T items;

    public ItemDto(String code, String message, T items) {
        this.code = code;
        this.message = message;
        this.items = items;
    }

    public static <T> ItemDtoBuilder<T> builder() {
        return new ItemDtoBuilder<T>();
    }

    public static class ItemDtoBuilder<T> {
        private String code = "00";
        private String message = "";
        private T items;

        ItemDtoBuilder() {
        }

        public ItemDtoBuilder<T> code(String code) {
            this.code = code;
            return this;
        }

        public ItemDtoBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ItemDtoBuilder<T> items(T items) {
            this.items = items;
            return this;
        }

        public ItemDto<T> build() {
            return new ItemDto<T>(code, message, items);
        }
    }
}