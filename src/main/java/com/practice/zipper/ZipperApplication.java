package com.practice.zipper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({
		"classpath:integration-infrastructure.xml"
})
public class ZipperApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipperApplication.class, args);
	}

}
