package com.zstiu.IoTService.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Datastream {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String tags;

    @Column(nullable = true)
    private String unit;
    @Column(nullable = true)
    private String unit_symbol;
    @Column(nullable = true)
    private String current_value;
//    @Column(nullable = true)
//    private String keys;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @CreatedDate
    private Date createTime;
    /**
     * 修改时间
     */
    @Column(name = "update_at")
    @LastModifiedDate
    private Date update_at;

//    @OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},fetch=FetchType.LAZY,mappedBy="manager")
//    private Set<User> users;

//    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.EAGER,optional=false)
    private int device_id;

    public int getDeviceId() {
        return device_id;
    }

    public void setDeviceId(int device_id) {
        this.device_id = device_id;
    }

//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
