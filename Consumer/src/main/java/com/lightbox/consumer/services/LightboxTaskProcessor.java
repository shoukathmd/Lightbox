package com.lightbox.consumer.services;

import com.lightbox.dto.LightboxRequestDTO;

public interface LightboxTaskProcessor {

    void process(LightboxRequestDTO task);
}
