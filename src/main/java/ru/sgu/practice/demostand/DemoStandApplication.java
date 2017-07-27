package ru.sgu.practice.demostand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import ru.sgu.practice.demostand.ui.GlobalVariables;
import ru.sgu.practice.demostand.ui.SetTime;

@SpringBootApplication
@ServletComponentScan
public class DemoStandApplication {

	public static void main(String[] args) {
		GlobalVariables.checkingFiles();
		new Thread(new SetTime()).start();
		SpringApplication.run(DemoStandApplication.class, args);
	}
}
