package br.com.apipixv3.apipixv3.configuration;

import org.modelmapper.ModelMapper;

public class ConfigModelMapper {

    private ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
