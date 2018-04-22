package com.miralcle.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by naonao on 18/4/22.
 */
@SpringBootApplication
@EnableEurekaServer
public class MiracleEurekaApplication2 {
    public static void main(String[] args) {
        MiracleEurekaApplication1.setProfile("peer2");
        SpringApplication.run(MiracleEurekaApplication2.class, args);
    }
}
