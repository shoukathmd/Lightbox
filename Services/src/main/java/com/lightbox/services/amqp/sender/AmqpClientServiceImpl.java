package com.lightbox.services.amqp.sender;


import com.lightbox.model.LightboxRequestModel;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AmqpClientServiceImpl implements AmqpClientService {

    private AmqpTemplate template;
    private String exchange;

    private FanoutExchange fanout;


    @Autowired
    public AmqpClientServiceImpl(AmqpTemplate template, @Value("${lightbox.exchange}") String exchange) {
        this.template = template;
        this.exchange = exchange;
    }

    public void sendTask(LightboxRequestModel lightboxRequestModel, String queue) {
        this.template.convertAndSend(exchange, queue, lightboxRequestModel);
    }
}
