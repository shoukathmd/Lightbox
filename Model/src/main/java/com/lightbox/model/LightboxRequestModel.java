package com.lightbox.model;


import com.lightbox.model.base.BaseModel;
import lombok.Data;

@Data
public class LightboxRequestModel extends BaseModel {
    private String requestId;
    private String msg;
}
