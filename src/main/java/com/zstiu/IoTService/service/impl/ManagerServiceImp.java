package com.zstiu.IoTService.service.impl;

import com.zstiu.IoTService.controller.ManagerController;
import com.zstiu.IoTService.domain.Manager;
import com.zstiu.IoTService.repository.ManagerRepository;
import com.zstiu.IoTService.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/4/10.
 */
@Service
public class ManagerServiceImp implements ManagerService{

    private static final Logger log = LoggerFactory.getLogger(ManagerServiceImp.class);

    @Autowired
    public ManagerRepository managerRepository;

    public boolean authenticateManager(String managerName, String password){
        Manager manager = managerRepository.findManager(managerName);
        if(password.equals(manager.getPassword())){
            return true;
        }else {
            return false;
        }
    }
}
