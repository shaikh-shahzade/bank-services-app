package com.demo.confgserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@SpringBootApplication
@EnableConfigServer
public class ConfgserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfgserverApplication.class, args);
	}

}
