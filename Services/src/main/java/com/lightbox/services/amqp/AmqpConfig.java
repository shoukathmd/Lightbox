package com.lightbox.services.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


import java.util.HashMap;
import java.util.Map;


@Configuration
@PropertySource(ignoreResourceNotFound = false, value = "classpath:/services.properties")
public class AmqpConfig {

    final Logger logger = LoggerFactory.getLogger(getClass());


    @Bean
    public TopicExchange exchange(@Value("${lightbox.exchange}") String exchange) {
        return new TopicExchange(exchange);
    }


    @Bean(name = "lightboxDefaultQueue")
    public Queue botDefaultQueue(@Value("${lightbox.task.queue}") String queue) {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 3600000);
        return new Queue(queue, true, false, false, args);
    }

    @Bean
    public Binding botDefaultQueueBinding(@Value("${lightbox.task.queue}") String routingKey, @Qualifier("lightboxDefaultQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapter,
//                                             @Value("${lightbox.task.quee}") String queueName) {
//
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(queueName);
//        container.setConcurrentConsumers(1);
//        container.setMaxConcurrentConsumers(1);
//        container.setDefaultRequeueRejected(false);
//        container.setAcknowledgeMode(AcknowledgeMode.NONE);
//        container.setChannelTransacted(false);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }

}
