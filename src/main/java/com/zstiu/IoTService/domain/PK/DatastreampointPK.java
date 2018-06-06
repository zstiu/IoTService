package com.zstiu.IoTService.domain.PK;

import lombok.Data;

import java.io.Serializable;

@Data
public class DatastreampointPK implements Serializable {

    private Long device_id;

    private String at;

}
