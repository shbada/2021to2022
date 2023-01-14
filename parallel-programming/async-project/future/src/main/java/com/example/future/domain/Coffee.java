package com.example.future.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity 로 보자.
 */
@Getter
@Setter
@Builder
public class Coffee {
    private String name;
    private int price;
}
