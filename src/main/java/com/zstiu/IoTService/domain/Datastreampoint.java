package com.zstiu.IoTService.domain;

import com.zstiu.IoTService.domain.PK.DatastreampointPK;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(DatastreampointPK.class)
@Data
public class Datastreampoint {

    @Id
    private Long device_id;
    @Id
    private String at;

    private String obliquity;
    private String temperature;
    private String humidity;
    private String location;
    private String position;
    private String pressure;

}
