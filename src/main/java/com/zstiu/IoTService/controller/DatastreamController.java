package com.zstiu.IoTService.controller;

import com.zstiu.IoTService.bean.ResponseBody;
import com.zstiu.IoTService.domain.Datastream;
import com.zstiu.IoTService.domain.Datastreampoint;
import com.zstiu.IoTService.repository.DatastreampointRepository;
import com.zstiu.IoTService.service.DatastreamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/datastream")
@Api(value = "dataStream", description = "对于数据流数据的一些读取操作")
public class DatastreamController {

    private static final Logger log = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private DatastreampointRepository datastreampointRepository;
    @Autowired
    private DatastreamService datastreamService;

    @ApiOperation(value="根据device_id获取所有数据流", notes="返回设备数据流列表")
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResponseBody getByDeviceId(HttpServletRequest request, HttpServletResponse response,
//                               @PathVariable Long device_id
                                      @RequestParam("device_id") Long device_id
    ) throws Exception {

        ResponseBody responseBody = new ResponseBody();

        List<Datastream> datastreams = datastreamService.getByDevice_id(device_id);

        responseBody.setSuccess(true);
        responseBody.setData(datastreams);

        return responseBody;

    }

    @ApiOperation(value="根据id和device_id和获取数据流", notes="返回相应数据流")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseBody getByIdAndDeviceId(HttpServletRequest request, HttpServletResponse response,
//                               @PathVariable Long device_id,
                               @RequestParam("device_id") Long device_id,
                               @PathVariable String id
    ) throws Exception {

        ResponseBody responseBody = new ResponseBody();

        Datastream datastream = datastreamService.getByIdAndDevice_id(device_id, id);

        responseBody.setSuccess(true);
        responseBody.setData(datastream);

        return responseBody;

    }

    @ApiOperation(value="根据device_id获取所有数据点", notes="返回设备数据点列表")
    @RequestMapping(value="/datapoint", method= RequestMethod.GET)
    public ResponseBody getAllDatapoint(HttpServletRequest request, HttpServletResponse response,
//                               @PathVariable Long device_id
                                      @RequestParam("device_id") Long device_id
    ) throws Exception {

        ResponseBody responseBody = new ResponseBody();

        List<Datastreampoint> datastreampoints = datastreampointRepository.findAllDatapoint(device_id);

        responseBody.setSuccess(true);
        responseBody.setData(datastreampoints);

        return responseBody;

    }

}
