package com.zstiu.IoTService.controller;

import com.zstiu.IoTService.bean.ResponseBody;
import com.zstiu.IoTService.domain.Manager;
import com.zstiu.IoTService.domain.Product;
import com.zstiu.IoTService.domain.User;
import com.zstiu.IoTService.repository.UserRepository;
import com.zstiu.IoTService.service.UserService;
import com.zstiu.IoTService.service.impl.UserServiceImp;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    // @PathVariable 获得请求url中的动态参数
    @RequestMapping(value = "/get/{name}")
    public User getUser(@PathVariable String name) {
        User user = new User();
//        UserRepository userRepository = new User();

//        user.setId(id);
//        user.setName(name);
//        user.setDate(new Date());
//        return user;
        user = userRepository.findUser(name);
        return user;
    }

    @ApiOperation(value="用户注册", notes="注册成功返回用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "userName", value = "用户名", required = true, paramType = "path"),
            @ApiImplicitParam(dataType = "String", name = "password", value = "密码", required = true, paramType = "path"),
    })
    @RequestMapping(value="/", method= RequestMethod.POST)
    public ResponseBody signUp(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody Map<String, Object> params) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        if (params.get("userName") == null || params.get("password") == null) {
            responseBody.setMessage("缺少参数或者参数格式错误");
            return responseBody;
        }

        String userName = params.get("userName") == "" ? "" : params.get("userName").toString();
        String password = params.get("password") == "" ? "" : params.get("password").toString();


        if(userRepository.findByName(userName) == null){
            User user = new User();
            user.setName(userName);
            user.setPassword(password);

            log.info(user.toString());
            userService.addNewUser(user);

            responseBody.setSuccess(true);
            responseBody.setMessage("注册成功");
            responseBody.setData(user);
        }
        else {
            responseBody.setMessage("用户名已存在");
        }

        return responseBody;
    }

    @ApiOperation(value="用户登录", notes="登录成功返回用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "userName", value = "用户名", required = true, paramType = "path"),
            @ApiImplicitParam(dataType = "String", name = "password", value = "密码", required = true, paramType = "path")
    })
    @RequestMapping(value="/login", method= RequestMethod.POST)
    public ResponseBody login(HttpServletRequest request, HttpServletResponse response,
                        @RequestBody Map<String, Object> params) throws Exception {

        ResponseBody responseBody = new ResponseBody();

        if (request.getSession().getAttribute("userName") != null) {
//            logger.info("用户名：" + request.getSession().getAttribute("username").toString());
            String userName = request.getSession().getAttribute("userName").toString();
            log.info("userName:"+ userName);
            User user = userRepository.findUser(userName);
            if (user == null) {
                request.setAttribute("info", "会话已失效,请重新登陆(user已被删除)!");
                responseBody.setMessage("会话已失效,请重新登陆(user已被删除)!");
//                logger.info(request.getAttribute("info"));
                return responseBody;
            }
            responseBody.setData(user);
            responseBody.setSuccess(true);
            responseBody.setMessage("您已登录");
            return responseBody;
        }

        if (params.get("userName") == null || params.get("password") == null) {
            responseBody.setMessage("缺少参数或者参数格式错误");
            return responseBody;
        }

        String userName = params.get("userName") == "" ? "" : params.get("userName").toString();
        String password = params.get("password") == "" ? "" : params.get("password").toString();
//        logger.info("login params: " + params);
        HttpSession session = request.getSession(false);
        User user = userRepository.findUser(userName);
//        logger.info(user);

        if (userService.authenticateUser(userName,password)) {
//        if (true) {
//            logger.info("验证通过");
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
//            session.setAttribute("userName", user.get("USER_NAME"));
//            session.setAttribute("displayName", user.get("USER_NAME"));
//            session.setAttribute("locale", "zh_CN");
//            long roleId = getUserRoleId(Long.valueOf(user.get("USER_ID")));
//            session.setAttribute("roleId", roleId);
//            session.setAttribute("timeZone", "GMT+0800");
//            if (request.getParameter("url") != null && !request.getParameter("url").isEmpty()
//                    && !"null".equals(request.getParameter("url"))) {
//                url = java.net.URLDecoder.decode(request.getParameter("url"), "utf-8");
//                System.out.println(url);
//                return "redirect:" + url;
//            }
            responseBody.setData(user);
            responseBody.setSuccess(true);
            return responseBody;
        } else {
            if (userName == "" && password == "") {
                request.setAttribute("info", "请输入用户名或密码!");
                responseBody.setMessage("请输入用户名或密码!");
            } else if (userName == "") {
                request.setAttribute("info", "请输入用户名!");
                responseBody.setMessage("请输入用户名!");
            } else if (password == "") {
                request.setAttribute("info", "请输入密码!");
                responseBody.setMessage("请输入密码!");
            } else {
                request.setAttribute("info", "用户名或密码错误!");
                responseBody.setMessage("用户名或密码错误!");
            }
            return responseBody;
        }
    }
}
