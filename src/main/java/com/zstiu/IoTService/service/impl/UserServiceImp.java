package com.zstiu.IoTService.service.impl;

import com.zstiu.IoTService.domain.Manager;
import com.zstiu.IoTService.domain.User;
import com.zstiu.IoTService.repository.ManagerRepository;
import com.zstiu.IoTService.repository.UserRepository;
import com.zstiu.IoTService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private static final Logger log = LoggerFactory.getLogger(ManagerServiceImp.class);

    @Autowired
    public UserRepository userRepository;

    public boolean authenticateUser(String userName, String password){
        User user = userRepository.findUser(userName);
        if(password.equals(user.getPassword())){
            return true;
        }else {
            return false;
        }
    }

    public void addNewUser(User user){
        userRepository.save(user);
    }
}
