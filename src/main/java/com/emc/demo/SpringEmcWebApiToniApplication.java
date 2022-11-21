package com.emc.demo;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

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
        if (System.getenv("DYNO") == null && isRunningInsideDocker() == false) {
            System.out.println("Application started ... launching browser now");
            browse("http://localhost:8080/swagger-ui/index.html");
        } else {
            System.out.println("You are in Heroku production environment");
        }
    }
    public static Boolean isRunningInsideDocker() {
        try (Stream<String> stream = Files.lines(Paths.get("/proc/1/cgroup"))) {
            return stream.anyMatch(line -> line.contains("/docker"));
        } catch (IOException e) {
            return false;
        }
    }
    public static void browse(String url) {
        try {
            System.setProperty("java.awt.headless", "false");
            Desktop desktop1 = Desktop.getDesktop();
            desktop1.browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
