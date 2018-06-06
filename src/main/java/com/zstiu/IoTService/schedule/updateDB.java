package com.zstiu.IoTService.schedule;

import com.zstiu.IoTService.bean.OnenetResponse;
import com.zstiu.IoTService.bean.ResponseBody;
import com.zstiu.IoTService.domain.Datapointhistory;
import com.zstiu.IoTService.domain.Datastream;
import com.zstiu.IoTService.domain.Datastreampoint;
import com.zstiu.IoTService.domain.Device;
import com.zstiu.IoTService.repository.DatastreampointRepository;
import com.zstiu.IoTService.service.DatapointhistoryService;
import com.zstiu.IoTService.service.DatastreamService;
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
import java.util.List;

@Component
public class updateDB {

    private static final Logger log = LoggerFactory.getLogger(updateDB.class);

    @Autowired
    private DatastreampointRepository datastreampointRepository;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DatastreamService datastreamService;
    @Autowired
    private DatapointhistoryService datapointhistoryService;

//    @Scheduled(fixedRate = 5000)
//    public void test(){
//        log.info("service is still running at " + new Date());
//    }

    @Scheduled(fixedRate = 360000)
//    @Scheduled(fixedRate = 5000)
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
            device.setId(Long.parseLong((String) _device.get("id")));
            device.setProtocol((String) _device.get("protocol"));
            device.setOnline((Boolean) _device.get("online"));
            device.setAuthInfo((String) _device.get("auth_info"));
            device.setTitle((String) _device.get("title"));

            deviceService.updateDevice(device);

            updateDataStreams(Long.parseLong((String) _device.get("id")));
            updateDatastreampoint(Long.parseLong((String) _device.get("id")));


        }
    }

    public void updateDataStreams(Long id){
        HashMap<String, String> map = new HashMap<>();
        map.put("per_page","100");
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("api-key", "yFklxQD=vZhpdKTQYkU=FQ65Txo=");

        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);

        String url = "http://api.heclouds.com/devices/" + id.toString() + "/datastreams";

        ResponseEntity<Object> responseEntity = restTemplate.exchange(url,HttpMethod.GET,requestEntity, Object.class, map);
        HashMap<String, Object> onenetResponse = (HashMap<String, Object>) responseEntity.getBody();
//        HashMap<String, Object> data = (HashMap<String, Object>) onenetResponse.get("data");

        ArrayList<HashMap<String, Object>> datastreams = (ArrayList<HashMap<String, Object>>) onenetResponse.get("data");

        for (HashMap<String, Object> _datastream:datastreams){
            Datastream datastream = new Datastream();
            datastream.setId((String) _datastream.get("id"));
            datastream.setDevice_id(id);
            datastream.setUuid((String) _datastream.get("uuid"));
            datastream.setUnit_symbol((String) _datastream.get("unit_symbol"));
            datastream.setUnit((String) _datastream.get("unit"));
//            String a = _datastream.get("uni").toString();
            try {
                datastream.setCurrent_value( _datastream.get("current_value").toString());

                Datapointhistory datapointhistory = new Datapointhistory();
                datapointhistory.setValue(_datastream.get("current_value").toString());
                datapointhistory.setDatastream_device_id(id);
                datapointhistory.setDatastream_id((String) _datastream.get("id"));
                datapointhistory.setAt((String) _datastream.get("update_at"));

                datapointhistoryService.updateDatapointhistory(datapointhistory);

                Datastreampoint datastreampoint = new Datastreampoint();

            }catch (Exception e){
                log.info("当前数据流：" + datastream.getId() + "---" + "无数据点上传");
                log.error(e.getMessage());
            }
//            datastream.setTags( _datastream.get("tags"));
//            datastream.setTags(String.Join(",", (String[])_datastream.get("tags").ToArray(typeof(String))));

            datastreamService.updateDataStream(datastream);

        }

    }

    public void updateDatastreampoint(Long id){
        HashMap<String, String> map = new HashMap<>();
        map.put("per_page","100");
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("api-key", "yFklxQD=vZhpdKTQYkU=FQ65Txo=");

        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);

        String url = "http://api.heclouds.com/devices/" + id.toString() + "/datapoints";

        ResponseEntity<Object> responseEntity = restTemplate.exchange(url,HttpMethod.GET,requestEntity, Object.class, map);
        HashMap<String, Object> onenetResponse = (HashMap<String, Object>) responseEntity.getBody();
//        HashMap<String, Object> data = (HashMap<String, Object>) onenetResponse.get("data");

        HashMap<String, Object> data = (HashMap<String, Object>) onenetResponse.get("data");
        ArrayList<HashMap<String, Object>> datastreams = (ArrayList<HashMap<String, Object>>) data.get("datastreams");

        Datastreampoint datastreampoint = new Datastreampoint();
        datastreampoint.setDevice_id(id);

        for (HashMap<String, Object> _datastream:datastreams){

            String _id = (String) _datastream.get("id");
            ArrayList<HashMap<String, Object>> datapoints = (ArrayList<HashMap<String, Object>>) _datastream.get("datapoints");
            HashMap<String, Object> datapoint = datapoints.get(0);

            switch (_id){
                case "obliquity":
                    try {
                        if(datapoint.get("at") != null){
                            datastreampoint.setAt(datapoint.get("at").toString());
                        }
                    }catch(Exception e){

                    }
                    datastreampoint.setObliquity(datapoint.get("value").toString());
                    break;
                case "temperature":
                    try {
                        if(datapoint.get("at") != null){
                            datastreampoint.setAt(datapoint.get("at").toString());
                        }
                    }catch(Exception e){

                    }
                    datastreampoint.setTemperature(datapoint.get("value").toString());
                    break;
                case "humidity":
                    try {
                        if(datapoint.get("at") != null){
                            datastreampoint.setAt(datapoint.get("at").toString());
                        }
                    }catch(Exception e){

                    }
                    datastreampoint.setHumidity(datapoint.get("value").toString());
                    break;
                case "location":
                    try {
                        if(datapoint.get("at") != null){
                            datastreampoint.setAt(datapoint.get("at").toString());
                        }
                    }catch(Exception e){

                    }
                    datastreampoint.setLocation(datapoint.get("value").toString());
                    break;
                case "position":
                    try {
                        if(datapoint.get("at") != null){
                            datastreampoint.setAt(datapoint.get("at").toString());
                        }
                    }catch(Exception e){

                    }
                    datastreampoint.setPosition(datapoint.get("value").toString());
                    break;
                case "pressure":
                    try {
                        if(datapoint.get("at") != null){
                            datastreampoint.setAt(datapoint.get("at").toString());
                        }
                    }catch(Exception e){

                    }
                    datastreampoint.setPressure(datapoint.get("value").toString());
                    break;
            }
        }
        datastreampointRepository.saveAndFlush(datastreampoint);

    }

}
