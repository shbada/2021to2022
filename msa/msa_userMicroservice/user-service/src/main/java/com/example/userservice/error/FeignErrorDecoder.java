package com.example.userservice.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Component /* 주입을 위해 */
public class FeignErrorDecoder implements ErrorDecoder {
    private final Environment environment;

    @Override
    public Exception decode(String methodkey, Response response) {
        switch (response.status()) {
            case 400:
                break;
            case 404:
                if (methodkey.contains("getOrders")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), environment.getProperty("order_is_empty"));
                }

                break;
            default:
                return new Exception();
        }
        return null;
    }
}
