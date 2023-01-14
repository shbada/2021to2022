package com.elk.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
        "ListPublicReservationEducation"
})
public class OpenApiBaseDto {
    @JsonProperty("ListPublicReservationEducation")
    private OpenApiDto ListPublicReservationEducation;
}
