package com.lightbox.converter.base;

import com.lightbox.dto.LightboxRequestDTO;
import com.lightbox.model.LightboxRequestModel;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface LightboxRequestConverter extends BaseConverter<LightboxRequestModel, LightboxRequestDTO> {

}
