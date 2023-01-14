package com.example.orderservice.elasticsearch.index;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@Document(indexName = "testIndex")
@Setting(settingPath = "elastic-setting.json")
public class ElasticSearchTestIndex {
    /* id */
    @Id
    @Field(type = FieldType.Long)
    private int reservationId;

    /* 서비스구분 */
    @Field(type = FieldType.Keyword)
    private String type;

}
