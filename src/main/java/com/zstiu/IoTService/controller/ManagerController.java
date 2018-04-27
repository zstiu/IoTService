package com.zstiu.IoTService.controller;

import com.zstiu.IoTService.bean.ResponseBody;
import com.zstiu.IoTService.domain.Product;
import com.zstiu.IoTService.domain.User;
import com.zstiu.IoTService.domain.Manager;
import com.zstiu.IoTService.repository.ManagerRepository;
import com.zstiu.IoTService.repository.ProductRepository;

import com.zstiu.IoTService.repository.UserRepository;
import com.zstiu.IoTService.requestBody.SignUpUser;
import com.zstiu.IoTService.requestBody.LoginManager;
import com.zstiu.IoTService.requestBody.SignUpManager;
import com.zstiu.IoTService.service.UserService;
import com.zstiu.IoTService.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/3/15.
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {

    private static final Logger log = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private UserService userService;


    @ApiOperation(value="管理员注册", notes="注册成功返回管理员信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(dataType = "String", name = "managerName", value = "管理员用户名", required = true, paramType = "path"),
//            @ApiImplicitParam(dataType = "String", name = "password", value = "管理员密码", required = true, paramType = "path"),
//            @ApiImplicitParam(dataType = "String", name = "APIKey", value = "对应产品的APIKey", required = true, paramType = "path")
//    })
    @RequestMapping(value="/", method= RequestMethod.POST)
    public ResponseBody signUp(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody SignUpManager signUpManager) throws Exception {
//                               @ApiParam(name="managerName",value="管理员用户名",required=true) @RequestParam(name = "managerName") String managerName,
//                               @ApiParam(name="password",value="管理员密码",required=true) @RequestParam(name = "password") String password,
//                               @ApiParam(name="APIKey",value="对应产品的APIKey",required=true) @RequestParam(name = "APIKey") String APIKey) throws Exception {

        ResponseBody responseBody = new ResponseBody();

        if (signUpManager.getManagerName() == null || signUpManager.getPassword() == null || signUpManager.getApiKey() == null) {
            responseBody.setMessage("缺少参数或者参数格式错误");
            return responseBody;
        }

        String managerName = signUpManager.getManagerName();
        String password = signUpManager.getPassword();
        String APIKey = signUpManager.getApiKey();

        Product product = productRepository.findByAPIKey(APIKey);


        if(managerRepository.findByName(managerName) == null){
            Manager manager = new Manager();
            manager.setProduct_id(product.getId());
            manager.setName(managerName);
            manager.setPassword(password);

            responseBody.setSuccess(true);
            responseBody.setMessage("注册成功");
            responseBody.setData(managerService.addManager(manager));
        }
        else if(product == null){
            responseBody.setMessage("APIKey不存在相应产品");
        }
        else {
            responseBody.setMessage("用户名已存在");
        }

        return responseBody;
    }


    @ApiOperation(value="管理员登录", notes="登录成功返回管理员信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(dataType = "String", name = "managerName", value = "管理员用户名", required = true, example = "test1", paramType = "body"),
//            @ApiImplicitParam(dataType = "String", name = "password", value = "管理员密码", required = true, example = "123456", paramType = "body")
//    })
    @RequestMapping(value="/login", method= RequestMethod.POST)
    public ResponseBody login(HttpServletRequest request, HttpServletResponse response,
//                              @RequestParam("managerName") String managerName,
//                              @RequestParam("password") String password) throws Exception {
                        @RequestBody LoginManager loginManager) throws Exception {
//    @ApiOperation(value="管理员登录", notes="登录成功返回管理员信息")
//    @PostMapping("/login")
//    public ResponseBody login(HttpServletRequest request, HttpServletResponse response,
//                              @ApiParam(name="managerName",value="管理员用户名",required=true) String managerName,
//                              @ApiParam(name="password",value="管理员密码",required=true) String password) throws Exception {

//        Map<String,Object> respBody = new HashMap<String,Object>();
        ResponseBody responseBody = new ResponseBody();

        if (request.getSession().getAttribute("managerName") != null) {
//            logger.info("用户名：" + request.getSession().getAttribute("username").toString());
//            String managerName = request.getSession().getAttribute("managerName").toString();
            String managerName = loginManager.getManagerName();
            log.info("managerName:"+ managerName);
            Manager manager = managerRepository.findManager(managerName);
            if (manager == null) {
                request.setAttribute("info", "会话已失效,请重新登陆(manager已被删除)!");
                responseBody.setMessage("会话已失效,请重新登陆(manager已被删除)!");
//                logger.info(request.getAttribute("info"));
                return responseBody;
            }
            responseBody.setData(manager);
            responseBody.setSuccess(true);
            responseBody.setMessage("您已登录");
            return responseBody;
        }

//        if (params.get("managerName") == null || params.get("password") == null) {
        if (loginManager.getManagerName() == null || loginManager.getPassword() == null) {
            responseBody.setMessage("缺少参数或者参数格式错误");
            return responseBody;
        }

//        String managerName = params.get("managerName") == "" ? "" : params.get("managerName").toString();
//        String password = params.get("password") == "" ? "" : params.get("password").toString();
        String managerName = loginManager.getManagerName();
        String password = loginManager.getPassword();
//        logger.info("login params: " + params);
        HttpSession session = request.getSession(false);
        Manager manager = managerRepository.findManager(managerName);
//        logger.info(user);

        if (managerService.authenticateManager(managerName,password)) {
//        if (true) {
//            logger.info("验证通过");
            session.setAttribute("managerId", manager.getId());
            session.setAttribute("managerName", manager.getName());
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
            responseBody.setData(manager);
            responseBody.setSuccess(true);
            responseBody.setMessage("登录成功");
            return responseBody;
        } else {
            System.out.println("su");
//            logger.info("验证不通过");
            if (managerName == "" && password == "") {
                request.setAttribute("info", "请输入管理员名和密码!");
                responseBody.setMessage("请输入用户名或密码!");
            } else if (managerName == "") {
                request.setAttribute("info", "请输入管理员名!");
                responseBody.setMessage("请输入用户名!");
            } else if (password == "") {
                request.setAttribute("info", "请输入密码!");
                responseBody.setMessage("请输入密码!");
            } else {
                request.setAttribute("info", "管理员名或密码错误!");
                responseBody.setMessage("用户名或密码错误!");
            }
//            logger.info(request.getAttribute("info"));
            return responseBody;
        }
//        return responseBody;
    }

    @ApiOperation(value="管理员添加普通用户", notes="添加成功返回用户信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(dataType = "String", name = "userName", value = "用户名", required = true, paramType = "path"),
//            @ApiImplicitParam(dataType = "String", name = "password", value = "用户密码", required = true, paramType = "path")
//    })
    @RequestMapping(value="/user", method= RequestMethod.POST)
    public ResponseBody addUser(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody SignUpUser signUpUser) throws Exception{
        ResponseBody responseBody = new ResponseBody();

        Manager currentManger = new Manager();
        if (request.getSession().getAttribute("managerName") != null) {
            String managerName = request.getSession().getAttribute("managerName").toString();
            log.info("currentManger:"+ managerName);
            currentManger = managerRepository.findManager(managerName);
            if (currentManger == null) {
                request.setAttribute("info", "会话已失效,请重新登陆(manager已被删除)!");
                responseBody.setMessage("会话已失效,请重新登陆(manager已被删除)!");
//                logger.info(request.getAttribute("info"));
                return responseBody;
            }
        }else {
            responseBody.setMessage("您未登录，无权限");
            return responseBody;
        }

        if (signUpUser.getUserName() == null || signUpUser.getPassword() == null) {
            responseBody.setMessage("缺少参数或者参数格式错误");
            return responseBody;
        }

        User addedUser = new User();

        String userName = signUpUser.getUserName();
        String password = signUpUser.getPassword();
        String type = signUpUser.getType();

        User userByName = userRepository.findByName(userName);
        if (userByName != null ){
            responseBody.setMessage("用户名已存在");
            return responseBody;
        }

        addedUser.setManager_id(currentManger.getId());
        addedUser.setName(userName);
        addedUser.setPassword(password);
        addedUser.setType(type);

        userService.addNewUser(addedUser);

        responseBody.setSuccess(true);
        responseBody.setMessage("添加用户成功");
        responseBody.setData(addedUser);

        return responseBody;
    }

    @ApiOperation(value="获取id对应管理员信息", notes="返回管理员信息")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseBody managerById(HttpServletRequest request, HttpServletResponse response,
                              @PathVariable Long id) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        Manager manager = managerRepository.findById(id);
//        logger.info(user);

        if (manager != null) {
                responseBody.setData(manager);
                responseBody.setSuccess(true);
                responseBody.setMessage("");
                return responseBody;
        } else {
            responseBody.setSuccess(false);
            responseBody.setMessage("无id对应管理员信息");
            return responseBody;
        }
//        return responseBody;
    }

    @ApiOperation(value="修改管理员信息", notes="返回修改后的管理员信息")
    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public ResponseBody putManagerById(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable Long id,
                                       @RequestBody LoginManager putManager) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        Manager manager = managerRepository.findById(id);
        manager.setId(id);
        manager.setName(putManager.getManagerName());

        Manager managerByName = managerRepository.findByName(putManager.getManagerName());

        if(managerByName !=null && managerByName.getId() != id){
            responseBody.setMessage("管理员名被占用");
            return responseBody;
        }

        manager.setPassword((putManager.getPassword()));

        try {

            Manager editedManager = managerRepository.saveAndFlush(manager);
            if(editedManager != null){
                responseBody.setSuccess(true);
                responseBody.setData(manager);
                responseBody.setMessage("修改成功");
            }else {
                responseBody.setMessage("修改失败");
            }
        }catch (Exception e){
            log.error("修改管理员信息失败：" + e);
        }


        return  responseBody;
    }


}
