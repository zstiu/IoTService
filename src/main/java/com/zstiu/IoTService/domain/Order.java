package com.zstiu.IoTService.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    //    @Column(nullable = false, unique=true)
    private String goodsName;

    //    @Column(nullable = false)
    private String goodsType;

    private Integer goodsNumber;

}
