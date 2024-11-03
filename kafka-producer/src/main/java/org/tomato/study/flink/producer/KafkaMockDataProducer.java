package org.tomato.study.flink.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * @author Tomato
 * Created on 2024.11.04
 */
public class KafkaMockDataProducer {

    private final String topic;
    private final ObjectMapper objectMapper;
    private final Producer<String, String> producer;

    public KafkaMockDataProducer(String topic) {
        this.topic = topic;
        this.objectMapper = new ObjectMapper();

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.10.123:9092");
        // 至少确保n个副本写入成功后才commit消息
        // props.put("min.insync.replicas", 1);
        // 存活节点都响应后才commit消息
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 60000);
        props.put(ProducerConfig.SOCKET_CONNECTION_SETUP_TIMEOUT_MS_CONFIG, 10000);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(props);
    }

    public void produce(MockStreamDTO dto) throws Exception {
        String uid = dto.getUid();
        String data = objectMapper.writeValueAsString(dto);
        ProducerRecord<String, String> msg = new ProducerRecord<>(
                topic,
                0,
                uid,
                data
        );

        RecordMetadata recordMetadata = producer.send(msg).get();
        System.out.printf("send message success [%d,%s]\n", recordMetadata.partition(), data);
    }
}
