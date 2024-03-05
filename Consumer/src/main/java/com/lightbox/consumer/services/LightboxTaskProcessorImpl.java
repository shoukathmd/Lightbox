package com.lightbox.consumer.services;

import com.lightbox.dto.LightboxRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LightboxTaskProcessorImpl implements LightboxTaskProcessor{

    @Value("${THIRD_PARTY_URL}")
    private String thirdPartyURL;

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void process(LightboxRequestDTO task) {

        RestTemplate restTemplate = new RestTemplate();

//        try {
//            HttpClientFactoryUtil.setRequestFactory(restTemplate);
//        } catch (KeyStoreException | NoSuchAlgorithmException | KeyManagementException e) {
//            logger.warn(e.getLocalizedMessage(), e);
//        }
        try {
            HttpEntity<String> request = new HttpEntity<>(task.getMsg());
            logger.info("Sending task [{}] to  3rd-party-service [{}]", task.getMsg(), thirdPartyURL);
            restTemplate.postForLocation(getUrl(), request);

        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
        }

    }

    protected String getUrl() {
        return thirdPartyURL;
    }

    protected HttpHeaders getHttpHeadersNoAuthentication() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Accept", "application/json");
        return httpHeaders;
    }
}
