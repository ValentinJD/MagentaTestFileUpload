package com.example.vv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class VvApplication {

    public static void main(String[] args) {
        SpringApplication.run(VvApplication.class, args);
    }

}
