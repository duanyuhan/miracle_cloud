package com.miracle.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by naonao on 18/4/20.
 */
@SpringBootApplication
@EnableEurekaClient
public class MiracleServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiracleServiceApplication.class, args);
    }
}
