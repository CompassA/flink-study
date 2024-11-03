package org.tomato.study.flink.producer;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Tomato
 * Created on 2024.11.04
 */
public class ProducerApp {

    public static void main(String[] args) throws Exception {
        KafkaMockDataProducer producer = new KafkaMockDataProducer("MOCK_FLINK_STREAM_SOURCE");

        while (true) {
            MockStreamDTO mockStreamDTO = new MockStreamDTO();
            mockStreamDTO.setUid(UUID.randomUUID().toString());
            mockStreamDTO.setData(BigDecimal.valueOf(Math.random() * 1000));
            mockStreamDTO.setCreateTime(System.currentTimeMillis());

            producer.produce(mockStreamDTO);

            Thread.sleep(Math.round(1 + 5 * Math.random()) * 1000);
        }
    }
}
