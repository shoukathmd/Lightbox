package com.lightbox.consumer.amqp;



import com.lightbox.consumer.exceptions.LightboxException;
import com.lightbox.consumer.services.LightboxDelegate;
import com.lightbox.model.LightboxRequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Receiver {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LightboxDelegate delegate;

    @Autowired
    private Sender sender;

    public void receiveMessage(LightboxRequestModel task) {
        logger.info("Lightbox task received [{}]", task);
        try {
            delegate.process(task);
        } catch (LightboxException ex) {
            logger.info("Request with id " + task.getRequestId() + " sending back to queue");
            sender.sendTask(task);
        }


    }

}