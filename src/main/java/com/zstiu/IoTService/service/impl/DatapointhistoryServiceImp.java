package com.zstiu.IoTService.service.impl;

import com.zstiu.IoTService.domain.Datapointhistory;
import com.zstiu.IoTService.repository.DatapointhistoryRepository;
import com.zstiu.IoTService.service.DatapointhistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatapointhistoryServiceImp implements DatapointhistoryService {

    @Autowired
    DatapointhistoryRepository datapointhistoryRepository;

    @Override
    public void updateDatapointhistory(Datapointhistory datapointhistory) {
        datapointhistoryRepository.saveAndFlush(datapointhistory);
    }
}
