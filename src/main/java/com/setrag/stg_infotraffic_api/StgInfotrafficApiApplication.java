package com.setrag.stg_infotraffic_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class StgInfotrafficApiApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry ->
            System.setProperty(entry.getKey(), entry.getValue())
        );
		SpringApplication.run(StgInfotrafficApiApplication.class, args);
		
	}

}
