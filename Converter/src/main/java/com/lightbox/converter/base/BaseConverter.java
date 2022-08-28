package com.lightbox.converter.base;


import org.mapstruct.InheritInverseConfiguration;


public interface BaseConverter<E, D> {

    E convertToModel(D dto);

    @InheritInverseConfiguration
    D convertToDto(E Model);



}