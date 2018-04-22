package com.miralcle.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by naonao on 18/4/22.
 */
@SpringBootApplication
@EnableEurekaServer
public class MiracleEurekaApplication1 {
    public static void main(String[] args) {
        setProfile("peer1");
        SpringApplication.run(MiracleEurekaApplication1.class, args);

    }
    public static void setProfile(String active) {
        String profile = "spring.profiles.active";
        System.setProperty(profile, active);
    }
}
