package com.roberto.SCCyL.view;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;

import javax.inject.Named;

@Named
@ApplicationScoped
public class DatosGeneralesView {

	private List<String> listaDeProvincias;
	private List<String> listaDeHospitales;
	private List<String> listaAmbitoSanitario;
	private List<String> listaCategoriaSanitario;
	private List<String> listaCentrosSanitarios;
	private List<String> listaGerencias;
	private boolean descargandoFicheros;
	private boolean ficherosDescargados;
	
	public List<String> getListaDeProvincias() {
		if(listaDeProvincias == null) {
			listaDeProvincias = new ArrayList<String>();
		}
		return listaDeProvincias;
	}

	public void setListaDeProvincias(List<String> listaDeProvincias) {
		this.listaDeProvincias = listaDeProvincias;
	}

	public List<String> getListaDeHospitales() {
		if(listaDeHospitales == null) {
			listaDeHospitales = new ArrayList<String>();
		}
		return listaDeHospitales;
	}

	public void setListaDeHospitales(List<String> listaDeHospitales) {
		this.listaDeHospitales = listaDeHospitales;
	}

	public List<String> getListaAmbitoSanitario() {
		if(listaAmbitoSanitario == null) {
			listaAmbitoSanitario = new ArrayList<String>();
		}
		return listaAmbitoSanitario;
	}

	public void setListaAmbitoSanitario(List<String> listaAmbitoSanitario) {
		this.listaAmbitoSanitario = listaAmbitoSanitario;
	}

	public List<String> getListaCategoriaSanitario() {
		if(listaCategoriaSanitario == null) {
			listaCategoriaSanitario = new ArrayList<String>();
		}
		return listaCategoriaSanitario;
	}

	public void setListaCategoriaSanitario(List<String> listaCategoriaSanitario) {
		this.listaCategoriaSanitario = listaCategoriaSanitario;
	}

	public List<String> getListaCentrosSanitarios() {
		if(listaCentrosSanitarios == null) {
			listaCentrosSanitarios = new ArrayList<String>();
		}
		return listaCentrosSanitarios;
	}

	public void setListaCentrosSanitarios(List<String> listaCentrosSanitarios) {
		this.listaCentrosSanitarios = listaCentrosSanitarios;
	}

	public List<String> getListaGerencias() {
		if(listaGerencias == null) {
			listaGerencias = new ArrayList<String>();
		}
		return listaGerencias;
	}

	public void setListaGerencias(List<String> listaGerencias) {
		this.listaGerencias = listaGerencias;
	}

	public boolean isDescargandoFicheros() {
		return descargandoFicheros;
	}

	public void setDescargandoFicheros(boolean descargandoFicheros) {
		this.descargandoFicheros = descargandoFicheros;
	}

	public boolean isFicherosDescargados() {
		return ficherosDescargados;
	}

	public void setFicherosDescargados(boolean ficherosDescargados) {
		this.ficherosDescargados = ficherosDescargados;
	}
	
}
