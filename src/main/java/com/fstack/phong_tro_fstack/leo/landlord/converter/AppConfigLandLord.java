package com.fstack.phong_tro_fstack.leo.landlord.converter;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigLandLord {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}