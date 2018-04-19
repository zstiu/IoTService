package com.zstiu.IoTService.service;

import com.zstiu.IoTService.domain.Manager;
import com.zstiu.IoTService.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/3/15.
 */
public interface ManagerService {

    public boolean authenticateManager(String managerName, String password);

    public Manager addManager(Manager manager);

}
