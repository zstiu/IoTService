package com.zstiu.IoTService.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@Table(name="`order`")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    //    @Column(nullable = false, unique=true)
    private String goodsName;

    //    @Column(nullable = false)
    private String goodsType;

    private Integer goodsNumber;

    private Long userId;

    private boolean complete;

}
