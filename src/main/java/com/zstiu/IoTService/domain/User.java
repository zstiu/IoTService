package com.zstiu.IoTService.domain;

import javax.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Created by Administrator on 2018/1/18.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class User {
    //
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @JsonIgnore
    private String name;

    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
    private String type;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @CreatedDate
    private Date createTime;
//    /**
//     * 创建人
//     */
//    @Column(name = "create_by")
//    @CreatedBy
//    private Long createBy;
    /**
     * 修改时间
     */
    @Column(name = "lastmodified_time")
    @LastModifiedDate
    private Date lastmodifiedTime;
//    /**
//     * 修改人
//     */
//    @Column(name = "lastmodified_by")
//    @LastModifiedBy
//    private String lastmodifiedBy;

    /**
     * ManyToOne：多对一的配置
     * cascade(级联)：all(所有)，merge(更新)，refresh(查询)，persistence(保存)，remove(删除)
     * fetch: eager:立即加载  one的一方默认是立即加载
     *            lazy:懒加载    many的一方默认是懒加载
     * optional:是否可选,外键是否允许为空
     *
     * JoinColumn:指定外键名
     *
     */
//    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.EAGER,optional=true)
//    @JoinColumn(nullable = true)
    @Column(nullable = true)
    private Long manager_id;

    public String toString(){
        return "User:name="+this.name + ",password:"+this.password+",manager_id:"+this.manager_id;
    }

}
