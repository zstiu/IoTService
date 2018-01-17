package com.zstiu.IoTService.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/1/17.
 */
public class IndexController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello 1223445";
    }
}
