package com.asc8.fileupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@SpringBootApplication(scanBasePackages = { "com.asc8.fileupload" })
@EnableAutoConfiguration
@EnableHypermediaSupport(type = { EnableHypermediaSupport.HypermediaType.HAL })
public class FileUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileUploadApplication.class, args);
	}
}
