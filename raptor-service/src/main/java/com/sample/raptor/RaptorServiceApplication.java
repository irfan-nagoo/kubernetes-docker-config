package com.sample.raptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RaptorServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RaptorServiceApplication.class, args);
    }

}
