package com.zstiu.IoTService.repository;

import com.zstiu.IoTService.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2018/3/15.
 */
public interface ManagerRepository extends JpaRepository<Manager, Long>  {

//    Manager findAllById();

    Manager findByName(String managerName);

    @Query("from Manager u where u.name=:name")
    Manager findManager(@Param("name") String name);

}
