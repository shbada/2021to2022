package com.example.userservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
// @AllArgsConstructor /* argument 다 가지고있는 생성자 */
// @NoArgsConstructor /* argument 없는 기본 생성자 */
public class Greeting {
    @Value("${greeting.message}")
    private String message;
}
