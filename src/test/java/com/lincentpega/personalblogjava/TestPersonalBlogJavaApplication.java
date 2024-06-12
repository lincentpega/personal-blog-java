package com.lincentpega.personalblogjava;

import org.springframework.boot.SpringApplication;

public class TestPersonalBlogJavaApplication {

	public static void main(String[] args) {
		SpringApplication.from(PersonalBlogJavaApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
