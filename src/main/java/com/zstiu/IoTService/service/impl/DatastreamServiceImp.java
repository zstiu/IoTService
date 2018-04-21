package com.zstiu.IoTService.service.impl;

import com.zstiu.IoTService.domain.Datastream;
import com.zstiu.IoTService.domain.Device;
import com.zstiu.IoTService.repository.DatastreamRepository;
import com.zstiu.IoTService.service.DatastreamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/4/22.
 */

@Service
public class DatastreamServiceImp implements DatastreamService {

    private static final Logger log = LoggerFactory.getLogger(ManagerServiceImp.class);

    @Autowired
    DatastreamRepository datastreamRepository;

    @Override
    public void updateDataStream(Datastream datastream) {
        datastreamRepository.saveAndFlush(datastream);
    }

    @Override
    public List<Datastream> getAll() {
        return null;
    }

    @Override
    public Datastream getById(Long id) {
        return null;
    }
}
