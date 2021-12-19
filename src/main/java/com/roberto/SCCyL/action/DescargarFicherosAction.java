package com.roberto.SCCyL.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.roberto.SCCyL.utils.ConstantesCSV;
import com.roberto.SCCyL.utils.ConstantesURL;
import com.roberto.SCCyL.view.DatosGeneralesView;

@Named
@ApplicationScoped
public class DescargarFicherosAction {

	public void descargarFicheroOcupacionHospitalaria() throws IOException {
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_OCUPACION_HOSPITALARIA;
		URL enlaceCSV = new URL(ConstantesURL.URL_OCUPACION_HOSPITALARIA);
		
		InputStream inputStream = enlaceCSV.openStream();
		Files.copy(inputStream, Paths.get(ficheroCSV), StandardCopyOption.REPLACE_EXISTING);
		
	}
	
	public void descargarFicheroSituacionHospitalaria() throws IOException {
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_SITUACION_HOSPITALARIA;
		URL enlaceCSV = new URL(ConstantesURL.URL_SITUACION_HOSPITALARIA);
		
		InputStream inputStream = enlaceCSV.openStream();
		Files.copy(inputStream, Paths.get(ficheroCSV), StandardCopyOption.REPLACE_EXISTING);
	}
	
	public void descargarFicheroSanitariosAfectados() throws IOException {
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_SANITARIOS_AFECTADOS;
		URL enlaceCSV = new URL(ConstantesURL.URL_SANITARIOS_AFECTADOS);
		
		InputStream inputStream = enlaceCSV.openStream();
		Files.copy(inputStream, Paths.get(ficheroCSV), StandardCopyOption.REPLACE_EXISTING);
	}
	
	public void descargarFicheroSituacionResidencias() throws IOException {
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_SITUACION_RESIDENCIAS;
		URL enlaceCSV = new URL(ConstantesURL.URL_SITUACION_RESIDENCIAS);
		
		InputStream inputStream = enlaceCSV.openStream();
		Files.copy(inputStream, Paths.get(ficheroCSV), StandardCopyOption.REPLACE_EXISTING);
	}
	
	public void descargarFicheroCentrosEducacion() throws IOException {
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_CENTROS_EDUCACION;
		URL enlaceCSV = new URL(ConstantesURL.URL_CENTROS_EDUCACION);
		
		InputStream inputStream = enlaceCSV.openStream();
		Files.copy(inputStream, Paths.get(ficheroCSV), StandardCopyOption.REPLACE_EXISTING);
	}
	
	public void descargarFicheroPruebasRealizadas() throws IOException {
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_PRUEBAS_REALIZADAS;
		URL enlaceCSV = new URL(ConstantesURL.URL_PRUEBAS_REALIZADAS);
		
		InputStream inputStream = enlaceCSV.openStream();
		Files.copy(inputStream, Paths.get(ficheroCSV), StandardCopyOption.REPLACE_EXISTING);
	}
	
	public void descargarFicheroVacunasRecibidas() throws IOException {
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_VACUNAS_RECIBIDAS;
		URL enlaceCSV = new URL(ConstantesURL.URL_VACUNAS_RECIBIDAS);
		
		InputStream inputStream = enlaceCSV.openStream();
		Files.copy(inputStream, Paths.get(ficheroCSV), StandardCopyOption.REPLACE_EXISTING);
	}
	
	public void descargarFicheroPersonasVacunadas() throws IOException {
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_PERSONAS_VACUNADAS;
		URL enlaceCSV = new URL(ConstantesURL.URL_PERSONAS_VACUNADAS);
		
		InputStream inputStream = enlaceCSV.openStream();
		Files.copy(inputStream, Paths.get(ficheroCSV), StandardCopyOption.REPLACE_EXISTING);
	}
	
	public void descargarFicheroFallecidosCovid() throws IOException {
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_FALLECIDOS_COVID;
		URL enlaceCSV = new URL(ConstantesURL.URL_FALLECIDOS_COVID);
		
		InputStream inputStream = enlaceCSV.openStream();
		Files.copy(inputStream, Paths.get(ficheroCSV), StandardCopyOption.REPLACE_EXISTING);
	}
	
	public void borrarFicheros() {
		File ficheroOcupacionHospitalaria = new File(ConstantesCSV.RUTA_FICHERO_OCUPACION_HOSPITALARIA);
		ficheroOcupacionHospitalaria.delete();
		File ficheroSituacionHospitales = new File(ConstantesCSV.RUTA_FICHERO_SITUACION_HOSPITALARIA);
		ficheroSituacionHospitales.delete();
		File ficheroSanitariosAfectados = new File(ConstantesCSV.RUTA_FICHERO_SANITARIOS_AFECTADOS);
		ficheroSanitariosAfectados.delete();
		File ficheroSituacionResidencias = new File(ConstantesCSV.RUTA_FICHERO_SITUACION_RESIDENCIAS);
		ficheroSituacionResidencias.delete();
		File ficheroCentrosEducacion = new File(ConstantesCSV.RUTA_FICHERO_CENTROS_EDUCACION);
		ficheroCentrosEducacion.delete();
		File ficheroPruebasRealizadas = new File(ConstantesCSV.RUTA_FICHERO_PRUEBAS_REALIZADAS);
		ficheroPruebasRealizadas.delete();
		File ficheroMortalidad = new File(ConstantesCSV.RUTA_FICHERO_FALLECIDOS_COVID);
		ficheroMortalidad.delete();
		File ficheroVacunasRealizadas = new File(ConstantesCSV.RUTA_FICHERO_PERSONAS_VACUNADAS);
		ficheroVacunasRealizadas.delete();
		File ficheroVacunasRecibidas = new File(ConstantesCSV.RUTA_FICHERO_VACUNAS_RECIBIDAS);
		ficheroVacunasRecibidas.delete();
		
	}
	
	public void descargarAllFicheros() throws IOException, InterruptedException {
		borrarFicheros();
		descargarFicheroOcupacionHospitalaria();
		descargarFicheroSituacionHospitalaria();
		descargarFicheroSanitariosAfectados();
		descargarFicheroSituacionResidencias();
		descargarFicheroCentrosEducacion();
		descargarFicheroPruebasRealizadas();
		descargarFicheroVacunasRecibidas();
		descargarFicheroPersonasVacunadas();
		descargarFicheroFallecidosCovid();
		Thread.sleep(60000 * 10);
		
		descargarAllFicheros();
	}

}
