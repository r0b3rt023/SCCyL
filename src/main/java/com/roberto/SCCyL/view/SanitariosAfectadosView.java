package com.roberto.SCCyL.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.roberto.SCCyL.bean.SanitariosAfectadosBean;
import com.roberto.SCCyL.bean.VacunasRecibidasBean;

@Named
@ViewScoped
public class SanitariosAfectadosView {

	@Autowired
	private CombosView combosView;

	private List<SanitariosAfectadosBean> listaSanitariosAfectadosGlobal;
	private List<SanitariosAfectadosBean> listaSanitariosAfectadosFiltrada;
	
	private boolean verListaFiltrada = false;
	
	private boolean verListaGlobal = true;
	
	/*LISTA PARA APLICAR FILTROS*/
	private List<String> listaProvinciasSeleccionadas;
	private List<String> listaAmbitosSeleccionados;
	private List<String> listaCategoriasSeleccionadas;
	private Date fechaInicialFiltro;
	private Date fechaFinalFiltro;
	private Date fechaMaxima = new Date();
	private Date fechaMinima;
	private Date fechaHoy = new Date();
	
	@PostConstruct
	public void init() throws IOException {
		//Vuelvo a cargar la lista con los datos globales
		setListaSanitariosAfectadosGlobal(null);
		setListaSanitariosAfectadosFiltrada(null);
		
		//Recargo los booleanos a su valor inicial y pongo a null las listas y las fechas
		setVerListaFiltrada(false);
		setVerListaGlobal(true);
		setListaProvinciasSeleccionadas(null);
		setListaAmbitosSeleccionados(null);
		setListaCategoriasSeleccionadas(null);
		setFechaInicialFiltro(null);
		setFechaFinalFiltro(null);
		setFechaMinima(null);
		setFechaMaxima(new Date());
	}

	public List<SanitariosAfectadosBean> getListaSanitariosAfectadosGlobal() throws IOException {
		if(listaSanitariosAfectadosGlobal == null) {
			listaSanitariosAfectadosGlobal = combosView.getListaSanitariosAfectados();
			Comparator<SanitariosAfectadosBean> comparator = Comparator.comparing(SanitariosAfectadosBean::getFecha).reversed()
					.thenComparing(SanitariosAfectadosBean::getProvincia);
			Collections.sort(listaSanitariosAfectadosGlobal, comparator);
		}
		return listaSanitariosAfectadosGlobal;
	}

	public void setListaSanitariosAfectadosGlobal(List<SanitariosAfectadosBean> listaSanitariosAfectadosGlobal) {
		this.listaSanitariosAfectadosGlobal = listaSanitariosAfectadosGlobal;
	}

	public List<SanitariosAfectadosBean> getListaSanitariosAfectadosFiltrada() {
		if(listaSanitariosAfectadosFiltrada == null) {
			listaSanitariosAfectadosFiltrada = new ArrayList<SanitariosAfectadosBean>();
		}
		return listaSanitariosAfectadosFiltrada;
	}

	public void setListaSanitariosAfectadosFiltrada(List<SanitariosAfectadosBean> listaSanitariosAfectadosFiltrada) {
		this.listaSanitariosAfectadosFiltrada = listaSanitariosAfectadosFiltrada;
	}

	public boolean isVerListaFiltrada() {
		return verListaFiltrada;
	}

	public void setVerListaFiltrada(boolean verListaFiltrada) {
		this.verListaFiltrada = verListaFiltrada;
	}

	public boolean isVerListaGlobal() {
		return verListaGlobal;
	}

	public void setVerListaGlobal(boolean verListaGlobal) {
		this.verListaGlobal = verListaGlobal;
	}

	public List<String> getListaProvinciasSeleccionadas() {
		return listaProvinciasSeleccionadas;
	}

	public void setListaProvinciasSeleccionadas(List<String> listaProvinciasSeleccionadas) {
		this.listaProvinciasSeleccionadas = listaProvinciasSeleccionadas;
	}

	public List<String> getListaAmbitosSeleccionados() {
		return listaAmbitosSeleccionados;
	}

	public void setListaAmbitosSeleccionados(List<String> listaAmbitosSeleccionados) {
		this.listaAmbitosSeleccionados = listaAmbitosSeleccionados;
	}

	public List<String> getListaCategoriasSeleccionadas() {
		return listaCategoriasSeleccionadas;
	}

	public void setListaCategoriasSeleccionadas(List<String> listaCategoriasSeleccionadas) {
		this.listaCategoriasSeleccionadas = listaCategoriasSeleccionadas;
	}

	public Date getFechaInicialFiltro() {
		return fechaInicialFiltro;
	}

	public void setFechaInicialFiltro(Date fechaInicialFiltro) {
		this.fechaInicialFiltro = fechaInicialFiltro;
	}

	public Date getFechaFinalFiltro() {
		return fechaFinalFiltro;
	}

	public void setFechaFinalFiltro(Date fechaFinalFiltro) {
		this.fechaFinalFiltro = fechaFinalFiltro;
	}

	public Date getFechaMaxima() {
		return fechaMaxima;
	}

	public void setFechaMaxima(Date fechaMaxima) {
		this.fechaMaxima = fechaMaxima;
	}

	public Date getFechaMinima() {
		return fechaMinima;
	}

	public void setFechaMinima(Date fechaMinima) {
		this.fechaMinima = fechaMinima;
	}

	public Date getFechaHoy() {
		return fechaHoy;
	}

	public void setFechaHoy(Date fechaHoy) {
		this.fechaHoy = fechaHoy;
	}
	
	
}
