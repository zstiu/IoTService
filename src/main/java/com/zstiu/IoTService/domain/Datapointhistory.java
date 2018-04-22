package com.zstiu.IoTService.domain;

import com.zstiu.IoTService.domain.PK.DatapointhitoryPK;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(DatapointhitoryPK.class)
@Data
public class Datapointhistory {
    @Id
//    @GeneratedValue
    private String at;
    @Id
    private String datastream_id;
    @Id
    private Long datastream_device_id;

    @Column(nullable = false)
    private String value;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @CreatedDate
    private Date createTime;

//    @OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},fetch=FetchType.LAZY,mappedBy="manager")
//    private Set<User> users;

//    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.EAGER,optional=false)

//    public Long getDatastreamId() {
//        return datastream_id;
//    }
//
//    public void setDatastreamId(Long datastream_id) {
//        this.datastream_id = datastream_id;
//    }
//
////    public Set<User> getUsers() {
////        return users;
////    }
////
////    public void setUsers(Set<User> users) {
////        this.users = users;
////    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getValue() {
//        return value;
//    }
//
//    public void setValue(String value) {
//        this.value = value;
//    }
}
