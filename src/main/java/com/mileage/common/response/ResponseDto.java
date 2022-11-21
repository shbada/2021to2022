package com.mileage.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {
   private int status = HttpStatus.OK.value();
   private String message = HttpStatus.OK.getReasonPhrase();
   private ItemDto<T> body;

   public ResponseDto(ItemDto<T> body) {
      this.body = body;
   }
}
