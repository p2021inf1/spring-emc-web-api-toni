package com.emc.demo;

import java.awt.Desktop;
import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringEmcWebApiToniApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEmcWebApiToniApplication.class, args);
	}
	
	@EventListener({ ApplicationReadyEvent.class })
	void applicationReadyEvent() {
		if (System.getenv("DYNO") == null) {
			System.out.println("Application started ... launching browser now");
			browse("http://localhost:8080/swagger-ui/index.html");
		} else {
			System.out.println("You are in Heroku production environment");
		}
	}

	public static void browse(String url) {
		System.setProperty("java.awt.headless", "false");
		Desktop desktop1 = Desktop.getDesktop();
		try {
			desktop1.browse(new URI(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
