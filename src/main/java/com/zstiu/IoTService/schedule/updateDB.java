package com.zstiu.IoTService.schedule;

import com.zstiu.IoTService.bean.OnenetResponse;
import com.zstiu.IoTService.bean.ResponseBody;
import com.zstiu.IoTService.domain.Device;
import com.zstiu.IoTService.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Component
public class updateDB {

    private static final Logger log = LoggerFactory.getLogger(updateDB.class);

    @Autowired
    private DeviceService deviceService;

    @Scheduled(fixedRate = 5000)
    public void test(){
        log.info("service is running at " + new Date());
    }

    @Scheduled(fixedRate = 1000)
    public void updateService(){
        HashMap<String, String> map = new HashMap<>();
        map.put("per_page","100");
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("api-key", "yFklxQD=vZhpdKTQYkU=FQ65Txo=");

        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);

        ResponseEntity<Object> responseEntity = restTemplate.exchange("http://api.heclouds.com/devices",HttpMethod.GET,requestEntity, Object.class, map);
//        Object body = responseEntity.getBody();
        HashMap<String, Object> onenetResponse = (HashMap<String, Object>) responseEntity.getBody();
        HashMap<String, Object> data = (HashMap<String, Object>) onenetResponse.get("data");
        ArrayList<HashMap<String, Object>> devices = (ArrayList<HashMap<String, Object>>) data.get("devices");

        for (HashMap<String, Object> _device:devices){
            Device device = new Device();
            device.setId(Integer.parseInt((String) _device.get("id")));
            device.setProtocol((String) _device.get("protocol"));
            device.setOnline((Boolean) _device.get("online"));
            device.setAuthInfo((String) _device.get("auth_info"));
            device.setTitle((String) _device.get("title"));

            deviceService.updateDevice(device);

        }
    }
}
