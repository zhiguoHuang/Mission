package com.guo.keepHard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KeepHardApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeepHardApplication.class, args);
    }

}
