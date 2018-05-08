package com.zstiu.IoTService.requestBody;

import lombok.Data;

@Data
public class EditCar {
    private Long id;

    private String driverName;

    private String phone;

    private String carNumber;
}
