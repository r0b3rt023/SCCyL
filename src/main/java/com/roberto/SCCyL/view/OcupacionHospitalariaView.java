package com.roberto.SCCyL.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.roberto.SCCyL.bean.OcupacionHospitalariaBean;

@Named
@ViewScoped
public class OcupacionHospitalariaView {

	@Autowired
	private CombosView combosView;

	private List<OcupacionHospitalariaBean> listaOcupacionHospitalariaGlobal;
	private List<OcupacionHospitalariaBean> listaOcupacionHospitalariaFiltrada;
	
	private boolean verListaFiltrada = false;
	
	private boolean verListaGlobal = true;
	
	/*LISTA PARA APLICAR FILTROS*/
	private List<String> listaProvinciasSeleccionadas;
	private List<String> listaHospitalesSeleccionados;
	private Date fechaInicialFiltro;
	private Date fechaFinalFiltro;
	private Date fechaMaxima = new Date();
	private Date fechaMinima;
	private Date fechaHoy = new Date();
	private List<OcupacionHospitalariaBean> listaValoresFiltrados;
	
	@PostConstruct
	public void init() throws IOException {
		//Vuelvo a cargar la lista con los datos globales
		setListaOcupacionHospitalariaGlobal(null);
		setListaOcupacionHospitalariaFiltrada(null);
		
		//Recargo los booleanos a su valor inicial y pongo a null las listas y las fechas
		setVerListaFiltrada(false);
		setVerListaGlobal(true);
		setListaProvinciasSeleccionadas(null);
		setListaHospitalesSeleccionados(null);
		setFechaInicialFiltro(null);
		setFechaFinalFiltro(null);
		setFechaMinima(null);
		setFechaMaxima(new Date());
	}
	
	
	public List<OcupacionHospitalariaBean> getListaOcupacionHospitalariaGlobal() throws IOException {
		if(listaOcupacionHospitalariaGlobal == null) {
			listaOcupacionHospitalariaGlobal = combosView.getListaOcupacionHospitalaria();
			Comparator<OcupacionHospitalariaBean> comparator = Comparator.comparing(OcupacionHospitalariaBean::getFecha).reversed()
					.thenComparing(OcupacionHospitalariaBean::getProvincia);
			Collections.sort(listaOcupacionHospitalariaGlobal, comparator);
		}
		return listaOcupacionHospitalariaGlobal;
	}

	public void setListaOcupacionHospitalariaGlobal(List<OcupacionHospitalariaBean> listaOcupacionHospitalariaGlobal) {
		this.listaOcupacionHospitalariaGlobal = listaOcupacionHospitalariaGlobal;
	}

	public List<OcupacionHospitalariaBean> getListaOcupacionHospitalariaFiltrada() {
		if(listaOcupacionHospitalariaFiltrada == null) {
			listaOcupacionHospitalariaFiltrada = new ArrayList<OcupacionHospitalariaBean>();
		}
		return listaOcupacionHospitalariaFiltrada;
	}

	public void setListaOcupacionHospitalariaFiltrada(List<OcupacionHospitalariaBean> listaOcupacionHospitalariaFiltrada) {
		this.listaOcupacionHospitalariaFiltrada = listaOcupacionHospitalariaFiltrada;
	}

	public List<String> getListaProvinciasSeleccionadas() {
		return listaProvinciasSeleccionadas;
	}

	public void setListaProvinciasSeleccionadas(List<String> listaProvinciasSeleccionadas) {
		this.listaProvinciasSeleccionadas = listaProvinciasSeleccionadas;
	}

	public List<String> getListaHospitalesSeleccionados() {
		return listaHospitalesSeleccionados;
	}

	public void setListaHospitalesSeleccionados(List<String> listaHospitalesSeleccionados) {
		this.listaHospitalesSeleccionados = listaHospitalesSeleccionados;
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

	public List<OcupacionHospitalariaBean> getListaValoresFiltrados() {
		if(listaValoresFiltrados == null) {
			listaValoresFiltrados = new ArrayList<OcupacionHospitalariaBean>();
		}
		return listaValoresFiltrados;
	}

	public void setListaValoresFiltrados(List<OcupacionHospitalariaBean> listaValoresFiltrados) {
		this.listaValoresFiltrados = listaValoresFiltrados;
	}

	
	
}
