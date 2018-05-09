package com.zstiu.IoTService.requestBody;

import lombok.Data;

@Data
public class AddOrderItem {
    private String goodsNumbering;

    private String deviceAuthInfo;

    private Long carId;

    private Long orderId;

}
