package com.zstiu.IoTService.controller;

import com.zstiu.IoTService.bean.ResponseBody;
import com.zstiu.IoTService.domain.Car;
import com.zstiu.IoTService.repository.CarRepository;
import com.zstiu.IoTService.requestBody.AddCar;
import com.zstiu.IoTService.requestBody.EditCar;
import com.zstiu.IoTService.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/car")
@Api(value = "car", description = "运输公司对于车辆信息的操作")
public class CarController {

    private static final Logger log = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;
    @Autowired
    private CarRepository carRepository;

    @ApiOperation(value="获取所有车辆", notes="返回所有车辆信息")
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResponseBody getAllCar(
    ) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        List<Car> cars = carService.getAll();

        responseBody.setSuccess(true);
        responseBody.setData(cars);

        return responseBody;
    }

    @ApiOperation(value="根据id获取车辆", notes="返回对应车辆信息")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseBody getCarById(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable Long id
    ) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        Car car = carRepository.findOne(id);

        responseBody.setSuccess(true);
        responseBody.setData(car);

        return responseBody;
    }

    @ApiOperation(value="根据user_id获取车辆", notes="返回所有对应车辆信息")
    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseBody getCarByUserId(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam("userId") Long userId
    ) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        List<Car> cars = carService.getCarByUserId(userId);

        responseBody.setSuccess(true);
        responseBody.setData(cars);

        return responseBody;
    }

    @ApiOperation(value="运输公司添加车辆", notes="添加成功返回车辆信息(访问前需先登录，带登陆后的session访问)")
    @RequestMapping(value="/", method= RequestMethod.POST)
    public ResponseBody addCar(
            HttpServletRequest request,
            @RequestBody AddCar addCar
    ) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        Car car = new Car();
        HttpSession session = request.getSession(false);

        String carNumber = addCar.getCarNumber();
        String driverName = addCar.getDriverName();
        String phone = addCar.getPhone();
        Long user_id = addCar.getUserId();
//        Long user_id = (Long) session.getAttribute("userId");
//
//        String userName = session.getAttribute("userName").toString();
//        String userType = session.getAttribute("userType").toString();
//
//        log.info("---" + user_id + "-" + userName + "-" + userType + "正在添加车辆信息");

        if(carNumber == null || driverName == null || phone == null || user_id == null){
            responseBody.setMessage("缺少参数");
            return responseBody;
        }

        car.setCarNumber(carNumber);
        car.setDriverName(driverName);
        car.setPhone(phone);
        car.setUserId(user_id);

        if(carRepository.findCarByCarNumber(car.getCarNumber()) == null ){
            try {
                Car addedCar = carService.addCar(car);

                log.info("车辆" + addedCar.getCarNumber() + "已添加");

                responseBody.setSuccess(true);
                String message;
                message = "添加成功";
                if(user_id == null){
                    message += "(你未登录，所以添加的车辆信息将会缺少公司信息)";
                }
                responseBody.setMessage(message);
                responseBody.setData(addedCar);
            }
            catch(Exception e){
                responseBody.setMessage("添加失败");
            }

        }
        else {
            responseBody.setMessage("车牌号已存在");
        }

        return responseBody;
    }

    @ApiOperation(value="运输公司修改车辆信息", notes="修改成功返回修改后的车辆信息")
    @RequestMapping(value="/", method= RequestMethod.PUT)
    public ResponseBody editCar(
            HttpServletRequest request,
            @RequestBody EditCar editCar
    ) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        Long id = editCar.getId();

        if(id == null){
            responseBody.setMessage("缺少参数");
            return responseBody;
        }

        Car car = carRepository.findOne(id);
        if(car == null){
            responseBody.setMessage("车辆不存在");
            return responseBody;
        }

        String carNumber = editCar.getCarNumber();
        String driverName = editCar.getDriverName();
        String phone = editCar.getPhone();

        if(carNumber != null){
            car.setCarNumber(carNumber);
        }
        if(driverName != null){
            car.setDriverName(driverName);
        }
        if(phone != null){
            car.setPhone(phone);
        }

        try {
            Car addedCar = carService.editCar(car);

            log.info("车辆" + addedCar.getCarNumber() + "信息已修改");

            responseBody.setSuccess(true);
            responseBody.setMessage("修改成功");
            responseBody.setData(addedCar);
        }
        catch(Exception e){
            responseBody.setMessage("修改失败");
        }

        return responseBody;
    }

}
