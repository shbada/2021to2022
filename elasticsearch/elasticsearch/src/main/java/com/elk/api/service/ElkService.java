package com.elk.api.service;

import com.elk.api.dto.index.ReservationIndex;
import com.elk.api.feign.ElasticSearchClient;
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
public class ElkService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ElasticSearchClient elasticSearchClient;

    /**
     * create Index (reservation)
     * @return
     */
    public String createIndex() {
        IndexCoordinates indexCoordinates = elasticsearchOperations.getIndexCoordinatesFor(ReservationIndex.class);
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(UUID.randomUUID().toString())
                .withObject(new ReservationIndex())
                .build();

        return elasticsearchOperations.index(indexQuery, indexCoordinates);
    }

    /**
     * insert 문서
     * @param reservationIndexList
     * @return
     */
    public List<ReservationIndex> insertDocument(List<ReservationIndex> reservationIndexList) {
        return (List<ReservationIndex>) elasticsearchOperations.save(reservationIndexList);
    }

    /**
     * reservation index 체
     * @return
     */
    public String selectIndex() {
        return elasticSearchClient.selectIndex();
    }
}
