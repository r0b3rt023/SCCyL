package com.roberto.SCCyL.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ConstantesFicheros {

	private final String RUTA_MASTER_MENU = "/includes/masterMenu.xhtml";
	private final String RUTA_MASTER_FOOTER = "/includes/masterFooter.xhtml";
	private final String RUTA_LOADER = "/includes/pantallaDescargandoFicheros.xhtml";
	private final String RUTA_OCUPACION_HOSPITALARIA = "/datos/ocupacionHospitalaria.xhtml";
	private final String RUTA_OCUPACION_HOSPITALARIA_REDIRECT = "/datos/ocupacionHospitalaria.xhtml?faces-redirect=true";
	private final String RUTA_SITUACION_HOSPITALARIA = "/datos/situacionHospitalaria.xhtml";
	private final String RUTA_SITUACION_HOSPITALARIA_REDIRECT = "/datos/situacionHospitalaria.xhtml?faces-redirect=true";
	private final String RUTA_SANITARIOS_AFECTADOS = "/datos/sanitariosAfectados.xhtml";
	private final String RUTA_SANITARIOS_AFECTADOS_REDIRECT = "/datos/sanitariosAfectados.xhtml?faces-redirect=true";
	private final String RUTA_SITUACION_RESIDENCIAS = "/datos/situacionResidencias.xhtml";
	private final String RUTA_SITUACION_RESIDENCIAS_REDIRECT = "/datos/situacionResidencias.xhtml?faces-redirect=true";
	private final String RUTA_CENTROS_EDUCACION = "/datos/centrosEducacion.xhtml";
	private final String RUTA_CENTROS_EDUCACION_REDIRECT = "/datos/centrosEducacion.xhtml?faces-redirect=true";
	private final String RUTA_PRUEBAS_REALIZADAS = "/datos/pruebasRealizadas.xhtml";
	private final String RUTA_PRUEBAS_REALIZADAS_REDIRECT = "/datos/pruebasRealizadas.xhtml?faces-redirect=true";
	private final String RUTA_ZONAS_DE_SALUD = "/datos/zonasDeSalud.xhtml";
	private final String RUTA_ZONAS_DE_SALUD_REDIRECT = "/datos/zonasDeSalud.xhtml?faces-redirect=true";
	private final String RUTA_VACUNAS_RECIBIDAS = "/datos/vacunasRecibidas.xhtml";
	private final String RUTA_VACUNAS_RECIBIDAS_REDIRECT = "/datos/vacunasRecibidas.xhtml?faces-redirect=true";
	private final String RUTA_PERSONAS_VACUNADAS = "/datos/personasVacunadas.xhtml";
	private final String RUTA_PERSONAS_VACUNADAS_REDIRECT = "/datos/personasVacunadas.xhtml?faces-redirect=true";
	private final String RUTA_FALLECIDOS_COVID = "/datos/fallecidosCovid.xhtml";
	private final String RUTA_FALLECIDOS_COVID_REDIRECT = "/datos/fallecidosCovid.xhtml?faces-redirect=true";
	
	
	private final String URL_MAPA = "https://analisis.datosabiertos.jcyl.es/explore/embed/dataset/tasa-enfermos-acumulados-por-areas-de-salud/custom/?&static=false&datasetcard=false";
	private final String URL_DATOS_JCYL = "https://analisis.datosabiertos.jcyl.es/pages/coronavirus/?flg=es";
	
	
	public String getRUTA_MASTER_MENU() {
		return RUTA_MASTER_MENU;
	}

	public String getRUTA_MASTER_FOOTER() {
		return RUTA_MASTER_FOOTER;
	}

	public String getRUTA_LOADER() {
		return RUTA_LOADER;
	}

	public String getRUTA_OCUPACION_HOSPITALARIA() {
		return RUTA_OCUPACION_HOSPITALARIA;
	}

	public String getRUTA_OCUPACION_HOSPITALARIA_REDIRECT() {
		return RUTA_OCUPACION_HOSPITALARIA_REDIRECT;
	}

	public String getRUTA_SITUACION_HOSPITALARIA() {
		return RUTA_SITUACION_HOSPITALARIA;
	}

	public String getRUTA_SITUACION_HOSPITALARIA_REDIRECT() {
		return RUTA_SITUACION_HOSPITALARIA_REDIRECT;
	}

	public String getRUTA_SANITARIOS_AFECTADOS() {
		return RUTA_SANITARIOS_AFECTADOS;
	}

	public String getRUTA_SANITARIOS_AFECTADOS_REDIRECT() {
		return RUTA_SANITARIOS_AFECTADOS_REDIRECT;
	}

	public String getRUTA_SITUACION_RESIDENCIAS() {
		return RUTA_SITUACION_RESIDENCIAS;
	}

	public String getRUTA_SITUACION_RESIDENCIAS_REDIRECT() {
		return RUTA_SITUACION_RESIDENCIAS_REDIRECT;
	}

	public String getRUTA_CENTROS_EDUCACION() {
		return RUTA_CENTROS_EDUCACION;
	}

	public String getRUTA_CENTROS_EDUCACION_REDIRECT() {
		return RUTA_CENTROS_EDUCACION_REDIRECT;
	}

	public String getRUTA_PRUEBAS_REALIZADAS() {
		return RUTA_PRUEBAS_REALIZADAS;
	}

	public String getRUTA_ZONAS_DE_SALUD() {
		return RUTA_ZONAS_DE_SALUD;
	}

	public String getRUTA_ZONAS_DE_SALUD_REDIRECT() {
		return RUTA_ZONAS_DE_SALUD_REDIRECT;
	}

	public String getRUTA_PRUEBAS_REALIZADAS_REDIRECT() {
		return RUTA_PRUEBAS_REALIZADAS_REDIRECT;
	}
	
	public String getRUTA_VACUNAS_RECIBIDAS() {
		return RUTA_VACUNAS_RECIBIDAS;
	}

	public String getRUTA_VACUNAS_RECIBIDAS_REDIRECT() {
		return RUTA_VACUNAS_RECIBIDAS_REDIRECT;
	}

	public String getRUTA_PERSONAS_VACUNADAS() {
		return RUTA_PERSONAS_VACUNADAS;
	}

	public String getRUTA_PERSONAS_VACUNADAS_REDIRECT() {
		return RUTA_PERSONAS_VACUNADAS_REDIRECT;
	}
	

	
	
	public String getRUTA_FALLECIDOS_COVID() {
		return RUTA_FALLECIDOS_COVID;
	}

	public String getRUTA_FALLECIDOS_COVID_REDIRECT() {
		return RUTA_FALLECIDOS_COVID_REDIRECT;
	}

	public String getURL_MAPA() {
		return URL_MAPA;
	}

	public String getURL_DATOS_JCYL() {
		return URL_DATOS_JCYL;
	}
	
}
