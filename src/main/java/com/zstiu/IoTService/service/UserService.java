package com.zstiu.IoTService.service;

import com.zstiu.IoTService.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/1/18.
 */

public interface UserService {
    public boolean authenticateUser(String userName, String password);

    public void addNewUser(User user);
}
