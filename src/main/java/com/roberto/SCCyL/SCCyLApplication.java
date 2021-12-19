package com.roberto.SCCyL;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.roberto.SCCyL.action.DescargarFicherosAction;

@SpringBootApplication
public class SCCyLApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(SCCyLApplication.class, args);
		DescargarFicherosAction descagarFicheros = new  DescargarFicherosAction();
		descagarFicheros.descargarAllFicheros();
	}
	

}
