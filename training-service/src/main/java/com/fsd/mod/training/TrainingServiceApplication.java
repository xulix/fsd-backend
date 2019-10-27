package com.fsd.mod.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TrainingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainingServiceApplication.class, args);
    }

}
