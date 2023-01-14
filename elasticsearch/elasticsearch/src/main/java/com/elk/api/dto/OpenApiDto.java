package com.elk.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({
        "list_total_count",
        "RESULT",
        "row"
})
public class OpenApiDto {
    @JsonProperty("list_total_count")
    private String listTotalCount;        /* 총 데이터 건수 */

    @JsonProperty("RESULT")
    private OpenApiResultDto result;        /* 요청과 코드, 메시지 */

    @JsonProperty("row")
    private List<OpenApiListDto> row;       /* 결과 리스트 */
}
