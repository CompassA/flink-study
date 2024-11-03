package org.tomato.study.flink.producer;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Mock数据流
 * @author Tomato
 * Created on 2024.11.04
 */
@Data
public class MockStreamDTO {

    /**
     * mock id
     */
    private String uid;

    /**
     * mock的浮点数据
     */
    private BigDecimal data;

    /**
     * 数据创建时间
     */
    private Long createTime;


}
