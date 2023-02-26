package com.esgi.groupe5.architrademe.ArchitrademeApplication;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.ModalityPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.RolePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArchitrademeApplication implements CommandLineRunner {
	@Autowired
	private RolePort roleServices;

	@Autowired
	private ModalityPort modalityServices;

	public static void main(String[] args) {
		SpringApplication.run(ArchitrademeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleServices.CreateRole();
		modalityServices.CreateModality();
	}

}
