package ru.chn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@ConfigurationPropertiesScan
@SpringBootApplication
public class ITHubApplication {
    public static void main(String[] args) {
        SpringApplication.run(ITHubApplication.class, args);
    }
}
