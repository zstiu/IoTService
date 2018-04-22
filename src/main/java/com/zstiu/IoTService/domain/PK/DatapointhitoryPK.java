package com.zstiu.IoTService.domain.PK;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class DatapointhitoryPK implements Serializable {

    private String at;

    private String datastream_id;

    private Long datastream_device_id;

}
