package com.zstiu.IoTService.controller;


import com.zstiu.IoTService.bean.ResponseBody;
import com.zstiu.IoTService.domain.Order;
import com.zstiu.IoTService.domain.OrderItem;
import com.zstiu.IoTService.repository.OrderItemRepository;
import com.zstiu.IoTService.repository.OrderRepository;
import com.zstiu.IoTService.requestBody.AddOrder;
import com.zstiu.IoTService.requestBody.AddOrderItem;
import com.zstiu.IoTService.service.OrderItemService;
import com.zstiu.IoTService.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
@Api(value = "order", description = "货运公司对于订单的操作")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderItemService orderItemService;

    @ApiOperation(value="获取所有订单(id,complete条件查询)", notes="返回所有订单信息(id已完成或未完成订单)")
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResponseBody getAllOrder(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value="id", required = false) Long id,
            @RequestParam(value="complete", required = false) Boolean complete
    ) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        List<Order> orders = new ArrayList<>();

        if(id != null && complete != null){
            orders = orderService.getOrderByIdAndComplete(id, complete);
        }else if(id != null){
            orders.add(orderRepository.findOne(id));
        }else if(complete != null){
            orders = orderService.getCompletedOrder(complete);
        }else {
            orders = orderService.getAll();
        }

//        if(complete == null){
//            orders = orderService.getAll();
//        }
//        else {
//            orders = orderService.getCompletedOrder(complete);
//        }

        responseBody.setSuccess(true);
        responseBody.setData(orders);

        return responseBody;
    }

    @ApiOperation(value="根据订单号获取订单信息", notes="返回订单信息()")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseBody getOrderById(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable Long id
    ) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        Order order;
        order = orderRepository.findOne(id);

        responseBody.setSuccess(true);

        if (order == null){
            responseBody.setMessage("无对应订单");
        }

        responseBody.setData(order);

        return responseBody;
    }

    @ApiOperation(value="根据订单号获取订单条目", notes="返回订单条目列表")
    @RequestMapping(value="/{orderId}/orderItem", method= RequestMethod.GET)
    public ResponseBody getOrderItemByOrderId(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable Long orderId
    ) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        List<OrderItem> orderItems;
        orderItems = orderItemRepository.findAllByOrderId(orderId);

        responseBody.setSuccess(true);

        if (orderItems.isEmpty()){
            responseBody.setMessage("无对应订单条目");
        }

        responseBody.setData(orderItems);

        return responseBody;
    }

    @ApiOperation(value="货运公司下订单操作", notes="下单成功返回订单信息（访问接口前需要货运类型的user先登录，带登陆后的session访问接口）")
    @RequestMapping(value="/", method= RequestMethod.POST)
    public ResponseBody addOrder(
            HttpServletRequest request,
            @RequestBody AddOrder addOrder
    ) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        Order order = new Order();
        HttpSession session = request.getSession(false);

        String goodsName = addOrder.getGoodsName();
        String goodsType = addOrder.getGoodsType();
        int goodsNumber = addOrder.getGoodsNumber();
        Long userId = addOrder.getUserId();
//        try {
//            userId = (Long) session.getAttribute("userId");
//        }catch (Exception e){
//            log.error("从session获取userId时出错" + e.toString());
//        }
//
//        if(userId == null ){
//            responseBody.setMessage("请先登录（货运公司）");
//            return responseBody;
//        }

        order.setGoodsName(goodsName);
        order.setGoodsType(goodsType);
        order.setGoodsNumber(goodsNumber);
        order.setUserId(userId);

        try {
            Order addedOrder = orderService.addOrder(order);

            responseBody.setSuccess(true);
            responseBody.setData(addedOrder);

            return responseBody;

        }catch (Exception e){
            responseBody.setMessage("添加失败");
        }

        return responseBody;
    }

    @ApiOperation(value="货运公司添加订单项操作", notes="完善成功返回订单信息（）")
    @RequestMapping(value="/item", method= RequestMethod.POST)
    public ResponseBody addOrderItem(
            HttpServletRequest request,
            @RequestBody AddOrderItem addOrderItem
    ) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        OrderItem orderItem = new OrderItem();
        HttpSession session = request.getSession(false);

        String goodsNumbering = addOrderItem.getGoodsNumbering();
        String deviceAuthInfo = addOrderItem.getDeviceAuthInfo();
        Long carId = addOrderItem.getCarId();
        Long orderId = addOrderItem.getOrderId();

//        OrderItem orderDeviceCar1
        orderItem.setGoodsNumbering(goodsNumbering);
        orderItem.setDeviceAuthInfo(deviceAuthInfo);
        orderItem.setCarId(carId);
        orderItem.setOrderId(orderId);

        try {
            OrderItem addedOrderItem = orderItemService.addOrderDeviceCar(orderItem);

            responseBody.setSuccess(true);
            responseBody.setData(addedOrderItem);

            return responseBody;

        }catch (Exception e){
            responseBody.setMessage("添加失败");
        }

        return responseBody;
    }

}
