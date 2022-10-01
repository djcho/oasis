package com.oasis.common.util;

import org.modelmapper.ModelMapper;

public final class ModelMapperUtils {

    private static ModelMapper modelMapper = new ModelMapper();
    
    private ModelMapperUtils() {}
    
    public static ModelMapper getModelMapper() {
        return modelMapper;
    }
    
}
