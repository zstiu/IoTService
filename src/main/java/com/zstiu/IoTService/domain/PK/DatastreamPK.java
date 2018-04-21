package com.zstiu.IoTService.domain.PK;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/22.
 */
@Data
@Getter
@Setter
public class DatastreamPK implements Serializable {

    private String id;

    private Long device_id;

}
