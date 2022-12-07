package com.example.orderservice.dto.orderkafka;

import lombok.Builder;
import lombok.Data;

@Data
@Builder /* 빌더패턴 */
public class Payload {
    private String order_id;
    private String user_id;
    private String product_id;
    private int qty;
    private int unit_price;
    private int total_price;
}
