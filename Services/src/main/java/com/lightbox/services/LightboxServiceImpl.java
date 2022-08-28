package com.lightbox.services;


import com.lightbox.converter.base.LightboxRequestConverter;
import com.lightbox.dto.LightboxRequestDTO;
import com.lightbox.model.LightboxRequestModel;
import com.lightbox.services.amqp.sender.AmqpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LightboxServiceImpl implements LightboxService {

    @Autowired
    private LightboxRequestConverter lightboxRequestConverter;

    @Autowired
    private AmqpClientService amqpClientService;

    @Value("${lightbox.task.queue}")
    private String queueName;

    @Override
    public void addToLightBoxQueue(LightboxRequestDTO lightboxRequestDTO) {

        LightboxRequestModel lightboxRequestModel = lightboxRequestConverter.convertToModel(lightboxRequestDTO);
        lightboxRequestModel.setRequestId(UUID.randomUUID().toString());
        amqpClientService.sendTask(lightboxRequestModel, queueName);
    }


}
