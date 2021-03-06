package com.zstiu.IoTService.controller;

import com.zstiu.IoTService.bean.ResponseBody;
import com.zstiu.IoTService.domain.User;
import com.zstiu.IoTService.repository.UserRepository;
import com.zstiu.IoTService.requestBody.LoginUser;
import com.zstiu.IoTService.requestBody.SignUpUser;
import com.zstiu.IoTService.service.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Api(value = "user", description = "普通用户(货运公司，运输公司)信息的一系列操作")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    // @PathVariable 获得请求url中的动态参数
//    @RequestMapping(value = "/get/{name}")
//    public User getUser(@PathVariable String name) {
//        User user = new User();
////        UserRepository userRepository = new User();
//
////        user.setId(id);
////        user.setName(name);
////        user.setDate(new Date());
////        return user;
//        user = userRepository.findUser(name);
//        return user;
//    }

    @ApiOperation(value="用户注册", notes="注册成功返回用户信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(dataType = "String", name = "userName", value = "用户名", required = true, paramType = "query",defaultValue="test1"),
//            @ApiImplicitParam(dataType = "String", name = "password", value = "密码", required = true, paramType = "query",defaultValue="123456"),
//            @ApiImplicitParam(dataType = "String", name = "type", value = "用户类型", required = true, paramType = "query",defaultValue="Cargo company"),
//    })
//    @ApiResponses({
//         @ApiResponse(code=400,message="请求参数没填好"),
//         @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
//     })
    @RequestMapping(value="/", method= RequestMethod.POST)
    public ResponseBody signUp(@RequestBody SignUpUser signUpUser
                               ) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        if (signUpUser.getUserName() == null || signUpUser.getPassword() == null || signUpUser.getType() == null) {
            responseBody.setMessage("缺少参数或者参数格式错误");
            return responseBody;
        }

        String userName = signUpUser.getUserName();
        String password = signUpUser.getPassword();
        String type = signUpUser.getType();


        if(userRepository.findByName(userName) == null){
            User user = new User();
            user.setName(userName);
            user.setPassword(password);
            user.setType(type);

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
//    @ApiImplicitParams({
//            @ApiImplicitParam(dataType = "String", name = "userName", value = "用户名", required = true, paramType = "path"),
//            @ApiImplicitParam(dataType = "String", name = "password", value = "密码", required = true, paramType = "path")
//    })
    @RequestMapping(value="/login", method= RequestMethod.POST)
    public ResponseBody login(HttpServletRequest request,
                              @RequestBody LoginUser loginUser) throws Exception {

        ResponseBody responseBody = new ResponseBody();

        if (request.getSession().getAttribute("userName") != null && request.getSession().getAttribute("userType") != null) {
//            logger.info("用户名：" + request.getSession().getAttribute("username").toString());
            String userName = request.getSession().getAttribute("userName").toString();
            String userType = request.getSession().getAttribute("userType").toString();
            log.info("userName:"+ userName);
            log.info("userType:"+ userType);
            User user = userRepository.findUser(userName);
            if (user == null) {
                request.setAttribute("info", "会话已失效,请重新登陆(user已被删除)!");
                responseBody.setMessage("会话已失效,请重新登陆(user已被删除)!");
//                logger.info(request.getAttribute("info"));
                return responseBody;
            }
            responseBody.setData(user);
            responseBody.setSuccess(true);
            responseBody.setMessage("您已登录(身份：" + userType + ")");
            return responseBody;
        }

        if (loginUser.getUserName() == null || loginUser.getPassword() == null) {
            responseBody.setMessage("缺少参数或者参数格式错误");
            return responseBody;
        }

        String userName = loginUser.getUserName();
        String password = loginUser.getPassword();
//        logger.info("login params: " + params);
        HttpSession session = request.getSession(false);
        User user = userRepository.findUser(userName);
//        logger.info(user);

        if (userService.authenticateUser(userName,password)) {
//        if (true) {
//            logger.info("验证通过");
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
            session.setAttribute("userType", user.getType());
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
            responseBody.setMessage("登录成功");
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

    @ApiOperation(value="获取id对应用户信息", notes="返回用户信息")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseBody userById(HttpServletRequest request,
                                    @PathVariable Long id) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        User user = userRepository.findById(id);
//        logger.info(user);

        if (user != null) {
            responseBody.setData(user);
            responseBody.setSuccess(true);
            responseBody.setMessage("");
            return responseBody;
        } else {
            responseBody.setSuccess(false);
            responseBody.setMessage("无id对应用户信息");
            return responseBody;
        }
    }

    @ApiOperation(value="修改用户信息", notes="返回修改后的用户信息")
    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public ResponseBody putManagerById(HttpServletRequest request,
                                       @PathVariable Long id,
                                       @RequestBody SignUpUser putUser) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        User user = userRepository.findById(id);
        user.setId(id);
        if (putUser.getUserName() != null) {
            User userByName = userRepository.findByName(putUser.getUserName());
            if(userByName !=null && userByName.getId() != id){
                responseBody.setMessage("用户名被占用");
                return responseBody;
            }
            user.setName(putUser.getUserName());
        }
        if (putUser.getPassword() != null) {
            user.setPassword((putUser.getPassword()));
        }
        if (putUser.getType() != null) {
            user.setType(putUser.getType());
        }

        try {

            User editedUser = userRepository.saveAndFlush(user);
            if(editedUser != null){
                responseBody.setSuccess(true);
                responseBody.setData(user);
                responseBody.setMessage("修改成功");
            }else {
                responseBody.setMessage("修改失败");
            }
        }catch (Exception e){
            log.error("修改用户信息失败：" + e);
        }


        return  responseBody;
    }

}
