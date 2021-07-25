package com.example.orderservice.elasticsearch;

import com.example.orderservice.elasticsearch.index.ElasticSearchTestIndex;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import java.util.UUID;

@RequiredArgsConstructor
public class ElasticSearchService {
    private final ElasticsearchOperations elasticsearchOperations;

    /**
     * create Index (test)
     * @return
     */
    public String createIndex() {
        IndexCoordinates indexCoordinates = elasticsearchOperations.getIndexCoordinatesFor(ElasticSearchTestIndex.class);
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(UUID.randomUUID().toString())
                .withObject(new ElasticSearchTestIndex())
                .build();

        return elasticsearchOperations.index(indexQuery, indexCoordinates);
    }
}
