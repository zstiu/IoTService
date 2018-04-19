package com.zstiu.IoTService.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class updateDB {

    private static final Logger log = LoggerFactory.getLogger(updateDB.class);

    @Scheduled(fixedRate = 5000)
    public void test(){
        log.info("service is running at " + new Date());
    }
}
