package ru.endpoint;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "ru.endpoint")
public class EndPointApplication {
    public static void main(String[] args) {
        SpringApplication.run(EndPointApplication.class, args);
    }
}
