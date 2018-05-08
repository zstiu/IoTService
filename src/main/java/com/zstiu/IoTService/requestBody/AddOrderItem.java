package com.zstiu.IoTService.requestBody;

import lombok.Data;

@Data
public class AddOrderItem {
    private String goodsNumbering;

    private Long deviceId;

    private Long carId;

    private Long orderId;

}
