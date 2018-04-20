package com.zstiu.IoTService.repository;

import com.zstiu.IoTService.domain.Manager;
import com.zstiu.IoTService.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Manager, Long> {
    @Query("from Product p where p.APIKey=:APIKey")
    Product findByAPIKey (@Param("APIKey") String APIKey);
}
