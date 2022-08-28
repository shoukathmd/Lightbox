package com.lightbox.services;


import com.lightbox.dto.LightboxRequestDTO;

public interface LightboxService {

    public void addToLightBoxQueue(LightboxRequestDTO request);
}
