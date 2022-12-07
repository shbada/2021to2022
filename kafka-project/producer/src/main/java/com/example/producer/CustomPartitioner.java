package com.example.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.InvalidRecordException;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

/*
프로듀서 사용환경에 따라 특정 데이터를 가지는 레코드를 특정 파티션으로 보내야할 때가 있다.
예를 들어, Pangyo라는 값을 가진 메시지 키가 0번 파티션으로 들어가야 한다고 가정하자.
기본 설정 파티셔너를 사용할 경우 메시지 키의 해시 값을 파티션에 매칭하여 데이터를 전송하므로
어느 파티션에 들어가는지 알 수 없다.
이때 Partitioner 인터페이스를 사용하여 사용자 정의 파티셔너를 생성하면 Pangyo라는 값을 가진
메시지 키에 대해서 무조건 파티션 0번으로 지정하도록 설정할 수 있다.
이렇게 지정할 경우 토픽의 파티션이 변경되더라도 Pangyo 라는 메시지 키를 가진 데이터는 파티션 0번에 적재된다.
 */
public class CustomPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes,
                         Object value, byte[] valueBytes, Cluster cluster) {
        if (keyBytes == null) {
            throw new InvalidRecordException("Need message key");
        }

        // 0번으로 지정
        if (((String) key).equals("testMessage")) {
            return 0;
        }

        List<PartitionInfo> partitions =  cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();

        // 주어진 레코드가 들어갈 파티션 번호
        // testMessage 가 아닌 메시지 키를 가진 레코드는 해시값을 지정하여 특정 파티션에 매칭되도록 설정한다.
        return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
