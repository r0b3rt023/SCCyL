package com.roberto.SCCyL.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.roberto.SCCyL.action.CargarDatosAction;
import com.roberto.SCCyL.bean.CentrosEducacionBean;
import com.roberto.SCCyL.bean.FallecidosCovidBean;
import com.roberto.SCCyL.bean.OcupacionHospitalariaBean;
import com.roberto.SCCyL.bean.PersonasVacunadasBean;
import com.roberto.SCCyL.bean.PruebasRealizadasBean;
import com.roberto.SCCyL.bean.SanitariosAfectadosBean;
import com.roberto.SCCyL.bean.SituacionHospitalariaBean;
import com.roberto.SCCyL.bean.SituacionResidenciasBean;
import com.roberto.SCCyL.bean.VacunasRecibidasBean;

@Named
@ApplicationScoped
public class CombosView {

	@Autowired
	private CargarDatosAction cargarDatosAction;

	/*LISTAS DE CLASES*/
	private List<OcupacionHospitalariaBean> listaOcupacionHospitalaria;
	private List<SituacionHospitalariaBean> listaSituacionHospitalaria;
	private List<SanitariosAfectadosBean> listaSanitariosAfectados;
	private List<SituacionResidenciasBean> listaSituacionResidencias;
	private List<CentrosEducacionBean> listaCentrosEducacion;
	private List<PruebasRealizadasBean> listaPruebasRealizadas;
	private List<PersonasVacunadasBean> listaPersonasVacunadas;
	private List<VacunasRecibidasBean> listaVacunasRecibidas;
	private List<FallecidosCovidBean> listaFallecidosCovid;
	
	public List<OcupacionHospitalariaBean> getListaOcupacionHospitalaria() throws IOException {
		if (listaOcupacionHospitalaria == null) {
			listaOcupacionHospitalaria = new ArrayList<OcupacionHospitalariaBean>();
			listaOcupacionHospitalaria = cargarDatosAction.cargarOcupacionHospitalaria();
		}
		return listaOcupacionHospitalaria;
	}

	public void setListaOcupacionHospitalaria(List<OcupacionHospitalariaBean> listaOcupacionHospitalaria) {
		this.listaOcupacionHospitalaria = listaOcupacionHospitalaria;
	}

	public List<SituacionHospitalariaBean> getListaSituacionHospitalaria() throws IOException {
		if(listaSituacionHospitalaria == null) {
			listaSituacionHospitalaria = new ArrayList<SituacionHospitalariaBean>();
			listaSituacionHospitalaria = cargarDatosAction.cargarSituacionHospitalaria();
		}
		return listaSituacionHospitalaria;
	}

	public void setListaSituacionHospitalaria(List<SituacionHospitalariaBean> listaSituacionHospitalaria) {
		this.listaSituacionHospitalaria = listaSituacionHospitalaria;
	}

	public List<SanitariosAfectadosBean> getListaSanitariosAfectados() throws IOException {
		if(listaSanitariosAfectados == null) {
			listaSanitariosAfectados = new ArrayList<SanitariosAfectadosBean>();
			listaSanitariosAfectados = cargarDatosAction.cargarSanitariosAfectados();
		}
		return listaSanitariosAfectados;
	}

	public void setListaSanitariosAfectados(List<SanitariosAfectadosBean> listaSanitariosAfectados) {
		this.listaSanitariosAfectados = listaSanitariosAfectados;
	}

	public List<SituacionResidenciasBean> getListaSituacionResidencias() throws IOException {
		if(listaSituacionResidencias == null) {
			listaSituacionResidencias = new ArrayList<SituacionResidenciasBean>();
			listaSituacionResidencias = cargarDatosAction.cargarSituacionResidencias();
		}
		return listaSituacionResidencias;
	}

	public void setListaSituacionResidencias(List<SituacionResidenciasBean> listaSituacionResidencias) {
		this.listaSituacionResidencias = listaSituacionResidencias;
	}

	public List<CentrosEducacionBean> getListaCentrosEducacion() throws IOException {
		if(listaCentrosEducacion == null) {
			listaCentrosEducacion = new ArrayList<CentrosEducacionBean>();
			listaCentrosEducacion = cargarDatosAction.cargarCentrosEducacion();
		}
		return listaCentrosEducacion;
	}

	public void setListaCentrosEducacion(List<CentrosEducacionBean> listaCentrosEducacion) {
		this.listaCentrosEducacion = listaCentrosEducacion;
	}

	public List<PruebasRealizadasBean> getListaPruebasRealizadas() throws IOException {
		if(listaPruebasRealizadas == null) {
			listaPruebasRealizadas = new ArrayList<PruebasRealizadasBean>();
			listaPruebasRealizadas = cargarDatosAction.cargarPruebasRealizadas();
		}
		return listaPruebasRealizadas;
	}

	public void setListaPruebasRealizadas(List<PruebasRealizadasBean> listaPruebasRealizadas) {
		this.listaPruebasRealizadas = listaPruebasRealizadas;
	}

	public List<PersonasVacunadasBean> getListaPersonasVacunadas() throws IOException {
		if(listaPersonasVacunadas == null) {
			listaPersonasVacunadas = new ArrayList<PersonasVacunadasBean>();
			listaPersonasVacunadas = cargarDatosAction.cargarPersonasVacunadas();
		}
		return listaPersonasVacunadas;
	}

	public void setListaPersonasVacunadas(List<PersonasVacunadasBean> listaPersonasVacunadas) {
		this.listaPersonasVacunadas = listaPersonasVacunadas;
	}

	public List<VacunasRecibidasBean> getListaVacunasRecibidas() throws IOException {
		if(listaVacunasRecibidas == null) {
			listaVacunasRecibidas = new ArrayList<VacunasRecibidasBean>();
			listaVacunasRecibidas = cargarDatosAction.cargarVacunasRecibidas();
		}
		return listaVacunasRecibidas;
	}

	public void setListaVacunasRecibidas(List<VacunasRecibidasBean> listaVacunasRecibidas) {
		this.listaVacunasRecibidas = listaVacunasRecibidas;
	}

	public List<FallecidosCovidBean> getListaFallecidosCovid() throws IOException {
		if(listaFallecidosCovid == null) {
			listaFallecidosCovid = new ArrayList<FallecidosCovidBean>();
			listaFallecidosCovid = cargarDatosAction.cargarFallecidosCOVID();
		}
		return listaFallecidosCovid;
	}

	public void setListaFallecidosCovid(List<FallecidosCovidBean> listaFallecidosCovid) {
		this.listaFallecidosCovid = listaFallecidosCovid;
	}
	
	
	
	
	
	
}
