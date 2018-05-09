package com.zstiu.IoTService.domain;

import com.zstiu.IoTService.domain.PK.OrderItemPK;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(OrderItemPK.class)
@Data
@Table(name="orderitem")
public class OrderItem {

//    @Id
//    @GeneratedValue
//    private Long id;

    //    @Column(nullable = false, unique=true)
    private String goodsNumbering;

    //    @Column(nullable = false)
    @Id
    private String deviceAuthInfo;

    private Long carId;

    @Id
    private Long orderId;

}
