package com.zstiu.IoTService.service;

import com.zstiu.IoTService.domain.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/1/18.
 */

public interface UserService {
    public boolean authenticateUser(String userName, String password);

    public void addNewUser(User user);

    public int currentUserAuthority(HttpServletRequest request);//当前用户的权限等级  0：未登录   1：管理员权限（可访问全部）  2：货运公司权限  3：运输公司权限
}
