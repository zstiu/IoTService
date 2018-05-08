package com.zstiu.IoTService.controller;


import com.zstiu.IoTService.bean.ResponseBody;
import com.zstiu.IoTService.domain.Order;
import com.zstiu.IoTService.domain.OrderItem;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private OrderItemService orderItemService;

    @ApiOperation(value="获取所有订单", notes="返回所有订单信息")
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResponseBody getAllOrder(
    ) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        List<Order> orders = orderService.getAll();

        responseBody.setSuccess(true);
        responseBody.setData(orders);

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
        Long userId = null;
        try {
            userId = (Long) session.getAttribute("userId");
        }catch (Exception e){
            log.error("从session获取userId时出错" + e.toString());
        }

        if(userId == null ){
            responseBody.setMessage("请先登录（货运公司）");
            return responseBody;
        }

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

    @ApiOperation(value="货运公司完善订单操作", notes="完善成功返回订单信息（访问接口前需要货运类型的user先登录，带登陆后的session访问接口）")
    @RequestMapping(value="/item", method= RequestMethod.POST)
    public ResponseBody addOrderItem(
            HttpServletRequest request,
            @RequestBody AddOrderItem addOrderItem
    ) throws Exception {
        ResponseBody responseBody = new ResponseBody();

        OrderItem orderItem = new OrderItem();
        HttpSession session = request.getSession(false);

        String goodsNumbering = addOrderItem.getGoodsNumbering();
        Long deviceNumbering = addOrderItem.getDeviceId();
        Long carNumbering = addOrderItem.getCarId();
        Long orderId = addOrderItem.getOrderId();

//        OrderItem orderDeviceCar1
        orderItem.setGoodsNumbering(goodsNumbering);
        orderItem.setDeviceNumbering(deviceNumbering);
        orderItem.setCarNumbering(carNumbering);
        orderItem.setOrderNumbering(orderId);

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
