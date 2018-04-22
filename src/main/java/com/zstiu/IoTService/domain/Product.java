package com.zstiu.IoTService.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
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
@Data
//@JsonIgnore
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String APIKey;

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

//    @OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},fetch=FetchType.LAZY,mappedBy="product")
//    private Set<Manager> managers;
//
//    public Set<Manager> getManagers() {
//        return managers;
//    }

//    public void setManagers(Set<Manager> managers) {
//        this.managers = managers;
//    }

}
