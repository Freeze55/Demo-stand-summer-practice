package ru.sgu.practice.demostand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import ru.sgu.practice.demostand.ui.GlobalVariables;

@SpringBootApplication
@ServletComponentScan
public class DemoStandApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoStandApplication.class, args);
	}
}
