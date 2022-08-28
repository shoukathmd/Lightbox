package com.lightbox.consumer.services;


import com.lightbox.consumer.amqp.Sender;
import com.lightbox.consumer.exceptions.LightboxException;
import com.lightbox.converter.base.LightboxRequestConverter;
import com.lightbox.model.LightboxRequestModel;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class LightboxDelegate {

    final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String LIGHTBOX_TASK_PROCESSOR_SERVICE = "taskProcessorService";

    @Autowired
    private LightboxTaskProcessor service;

    @Autowired
    private LightboxRequestConverter lightboxRequestConverter;




    @RateLimiter(name = LIGHTBOX_TASK_PROCESSOR_SERVICE, fallbackMethod = "rateLimiterFallback")
    public void process(LightboxRequestModel task) {
        logger.info("Light box task received [{}]", task);
        try {
            service.process(lightboxRequestConverter.convertToDto(task));
        } catch (RuntimeException ex) {
            logger.warn(ex.getLocalizedMessage(), ex);
        }
    }

    public void rateLimiterFallback(Exception ex) throws Exception {
        throw new LightboxException("Rate limiter : Sending request back to queue");

    }

}
