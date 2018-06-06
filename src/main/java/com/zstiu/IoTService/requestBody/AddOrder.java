package com.zstiu.IoTService.requestBody;

import lombok.Data;

@Data
public class AddOrder {
    private String goodsName;

    private String goodsType;

    private Integer goodsNumber;

    private Long userId;
}
