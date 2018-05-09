package com.zstiu.IoTService.domain.PK;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderItemPK implements Serializable {

    private String deviceAuthInfo;

    private Long orderId;

}
