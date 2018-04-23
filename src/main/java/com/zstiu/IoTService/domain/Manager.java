package com.zstiu.IoTService.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/15.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Manager {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false , unique = true)
    private String name;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

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

//    @JsonIgnore
//    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.EAGER,optional=false)
    private Long product_id;
}
