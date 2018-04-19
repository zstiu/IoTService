package com.zstiu.IoTService.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Administrator on 2018/4/14.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Device {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String tags;

    @Column(nullable = false)
    private boolean online;
    @Column(nullable = true)
    private String protocol;
    @Column(nullable = true)
    private String title;
    @Column(nullable = true)
    private String description;
    @Column(nullable = true)
//    private String private;
    private String location;
    @Column(nullable = true)
    private String authInfo;
    @Column(nullable = true)
    private String other;
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
    @Column(name = "lastmodified_time")
    @LastModifiedDate
    private Date lastmodifiedTime;

//    @OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},fetch=FetchType.LAZY,mappedBy="manager")
//    private Set<User> users;

//    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.EAGER,optional=false)
    private Long product_id;

    public Long getProductId() {
        return product_id;
    }

    public void setProductId(Long product_id) {
        this.product_id = product_id;
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

//    public boolean getOnline() {
//        return online;
//    }
//
//    public void setOnline(boolean online) {
//        this.online = online;
//    }
}
