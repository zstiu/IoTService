package com.zstiu.IoTService.controller;

import com.zstiu.IoTService.domain.User;
import com.zstiu.IoTService.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/17.
 */
@RestController
public class IndexController {

    @Autowired
    private UserRepository userRepository;

//    @RequestMapping("/hello")
//    public String index() {
//        return "Hello 1223445";
//    }
//
//    // @RequestParam 简单类型的绑定，可以出来get和post
//    @RequestMapping("/get")
//    public HashMap<String, Object> get(@RequestParam String name) {
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put("title", "hello world");
//        map.put("name", name);
//        return map;
//    }
//
//    // @PathVariable 获得请求url中的动态参数
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
//
//    @RequestMapping("/setsession/{age}")
//    @ResponseBody
//    public String TestSession(HttpServletRequest req, HttpServletResponse resp, @PathVariable String age){
//        req.getSession().setAttribute("age", age);
//        String a = (String) req.getSession().getAttribute("age");
//        return a;
//    }
//
//    @RequestMapping("/getsession")
//    public String getSession(HttpServletRequest req, HttpServletResponse resp){
//        String a = (String) req.getSession().getAttribute("age");
//        System.out.println("a:" + a);
//        return a;
////        return "/login";
//    }

//    @RequestMapping(value="/login", method= RequestMethod.POST)
//    public String login(HttpServletRequest request, HttpServletResponse response,
//                        @RequestBody Map<String, Object> params) throws Exception {
//
//        Map<String,Object> respBody = new HashMap<String,Object>();
//
//        if (request.getSession().getAttribute("userName") != null) {
////            logger.info("用户名：" + request.getSession().getAttribute("username").toString());
//            String userName = request.getSession().getAttribute("userName").toString();
//            System.out.println("userName:"+ userName);
//            User user = userRepository.findUser(userName);
//            if (user == null) {
//                request.setAttribute("info", "会话已失效,请重新登陆!");
////                logger.info(request.getAttribute("info"));
//                return "/login";
//            }
//            return "already login";
//        }
//
//        if (params.get("userName") == null || params.get("password") == null) {
//            return "/login";
//        }
//
//        String userName = params.get("userName") == "" ? "" : params.get("userName").toString();
//        String password = params.get("password") == "" ? "" : params.get("password").toString();
////        logger.info("login params: " + params);
//        HttpSession session = request.getSession(false);
//        User user = userRepository.findUser(userName);
////        logger.info(user);
//
////        if (UserUtils.authenticatePasswordHap(password, user.get("PASSWORD_ENCRYPTED"))) {
//        if (true) {
////            logger.info("验证通过");
//            session.setAttribute("userId", user.getId());
//            session.setAttribute("userName", user.getName());
////            session.setAttribute("userName", user.get("USER_NAME"));
////            session.setAttribute("displayName", user.get("USER_NAME"));
////            session.setAttribute("locale", "zh_CN");
////            long roleId = getUserRoleId(Long.valueOf(user.get("USER_ID")));
////            session.setAttribute("roleId", roleId);
////            session.setAttribute("timeZone", "GMT+0800");
////            if (request.getParameter("url") != null && !request.getParameter("url").isEmpty()
////                    && !"null".equals(request.getParameter("url"))) {
////                url = java.net.URLDecoder.decode(request.getParameter("url"), "utf-8");
////                System.out.println(url);
////                return "redirect:" + url;
////            }
//            return "success";
//        } else {
////            logger.info("验证不通过");
//            if (userName == "" && password == "") {
//                request.setAttribute("info", "请输入用户名或密码!");
//            } else if (userName == "") {
//                request.setAttribute("info", "请输入用户名!");
//            } else if (password == "") {
//                request.setAttribute("info", "请输入密码!");
//            } else {
//                request.setAttribute("info", "用户名或密码错误!");
//            }
////            logger.info(request.getAttribute("info"));
//            return "/login";
//        }
//    }

}
