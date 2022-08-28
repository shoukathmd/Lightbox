package com.lightbox.consumer.amqp;


import com.lightbox.model.LightboxRequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Sender {

    final Logger logger = LoggerFactory.getLogger(getClass());

    private AmqpTemplate template;
    private String exchange;
    private String routingKey;


    @Autowired
    public Sender(AmqpTemplate template,
                  @Value("${lightbox.exchange}") String exchange,
                  @Value("${lightbox.task.queue}") String routingKey) {

        this.template = template;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }


    public void sendTask(LightboxRequestModel task) {
        this.template.convertAndSend(exchange, routingKey, task);
    }


}