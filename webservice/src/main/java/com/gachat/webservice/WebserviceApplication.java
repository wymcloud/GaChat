package com.gachat.webservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebserviceApplication  implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WebserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}

