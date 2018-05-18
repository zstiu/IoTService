package com.zstiu.IoTService.requestBody;

import lombok.Data;

@Data
public class AddCar {
    private String driverName;

    private String phone;

    private String carNumber;

    private Long userId;
}
