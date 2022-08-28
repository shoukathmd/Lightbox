package com.lightbox.services.amqp.sender;


import com.lightbox.model.LightboxRequestModel;



public interface AmqpClientService {

    public void sendTask(LightboxRequestModel lightboxRequestModel, String queue);

}
