package com.zstiu.IoTService.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Car {
    @Id
    @GeneratedValue
    private Long id;

//    @Column(nullable = false, unique=true)
    private String driverName;

//    @Column(nullable = false)
    private String phone;

    private String carNumber;

    private Long userId;
}
