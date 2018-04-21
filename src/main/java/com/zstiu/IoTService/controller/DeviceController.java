package com.zstiu.IoTService.controller;

import com.zstiu.IoTService.bean.ResponseBody;
import com.zstiu.IoTService.domain.Device;
import com.zstiu.IoTService.service.DeviceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private static final Logger log = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private DeviceService deviceService;

    @ApiOperation(value="获取所有设备", notes="返回设备列表")
    @RequestMapping(value="/all", method= RequestMethod.GET)
    public ResponseBody signUp(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ResponseBody responseBody = new ResponseBody();

        List<Device> devices = deviceService.getAll();

        responseBody.setSuccess(true);
        responseBody.setData(devices);

        return responseBody;
    }

    @ApiOperation(value="根据id获取设备", notes="返回设备数据")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseBody signUp(HttpServletRequest request, HttpServletResponse response,
                               @PathVariable Long id
                               ) throws Exception {

        ResponseBody responseBody = new ResponseBody();

        Device device = deviceService.getById(id);

        responseBody.setSuccess(true);
        responseBody.setData(device);

        return responseBody;
    }

}
