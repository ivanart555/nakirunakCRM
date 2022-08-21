package com.ivanart555.nakirunakcrm.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NakirunakcrmConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
