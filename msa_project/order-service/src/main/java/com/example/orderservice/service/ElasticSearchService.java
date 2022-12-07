package com.example.orderservice.service;

import com.example.orderservice.elasticsearch.index.ElasticSearchTestIndex;
import com.example.orderservice.feign.ElasticSearchClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ElasticSearchService {
    private final ElasticsearchOperations elasticsearchOperations;
    private final ElasticSearchClient elasticSearchClient;

    /**
     * create Index (reservation)
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

    /**
     * insert 문서
     * @param reservationIndexList
     * @return
     */
    public List<ElasticSearchTestIndex> insertDocument(List<ElasticSearchTestIndex> reservationIndexList) {
        return (List<ElasticSearchTestIndex>) elasticsearchOperations.save(reservationIndexList);
    }

    /**
     * reservation index 체크
     * @return
     */
    public String selectIndex() {
        return elasticSearchClient.selectIndex();
    }
}
