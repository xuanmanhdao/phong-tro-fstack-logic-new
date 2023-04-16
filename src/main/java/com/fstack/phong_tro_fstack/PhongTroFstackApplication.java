package com.fstack.phong_tro_fstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PhongTroFstackApplication {

  public static void main(String[] args) {
    SpringApplication.run(PhongTroFstackApplication.class, args);
  }

}
