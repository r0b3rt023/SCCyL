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

import com.roberto.SCCyL.bean.OcupacionHospitalariaBean;
import com.roberto.SCCyL.bean.SituacionHospitalariaBean;

@Named
@ViewScoped
public class SituacionHospitalariaView {
	@Autowired
	private CombosView combosView;

	private List<SituacionHospitalariaBean> listaSituacionHospitalariaGlobal;
	private List<SituacionHospitalariaBean> listaSituacionHospitalariaFiltrada;
	
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
		setListaSituacionHospitalariaGlobal(null);
		setListaSituacionHospitalariaFiltrada(null);
		
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

	public List<SituacionHospitalariaBean> getListaSituacionHospitalariaGlobal() throws IOException {
		if(listaSituacionHospitalariaGlobal == null) {
			listaSituacionHospitalariaGlobal = combosView.getListaSituacionHospitalaria();
			Comparator<SituacionHospitalariaBean> comparator = Comparator.comparing(SituacionHospitalariaBean::getFecha).reversed()
					.thenComparing(SituacionHospitalariaBean::getProvincia);
			Collections.sort(listaSituacionHospitalariaGlobal, comparator);
		}
		return listaSituacionHospitalariaGlobal;
	}

	public void setListaSituacionHospitalariaGlobal(List<SituacionHospitalariaBean> listaSituacionHospitalariaGlobal) {
		this.listaSituacionHospitalariaGlobal = listaSituacionHospitalariaGlobal;
	}

	public List<SituacionHospitalariaBean> getListaSituacionHospitalariaFiltrada() {
		if(listaSituacionHospitalariaFiltrada == null) {
			listaSituacionHospitalariaFiltrada = new ArrayList<SituacionHospitalariaBean>();
		}
		return listaSituacionHospitalariaFiltrada;
	}

	public void setListaSituacionHospitalariaFiltrada(List<SituacionHospitalariaBean> listaSituacionHospitalariaFiltrada) {
		this.listaSituacionHospitalariaFiltrada = listaSituacionHospitalariaFiltrada;
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

	public List<String> getListaHospitalesSeleccionados() {
		return listaHospitalesSeleccionados;
	}

	public void setListaHospitalesSeleccionados(List<String> listaHospitalesSeleccionados) {
		this.listaHospitalesSeleccionados = listaHospitalesSeleccionados;
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
		return listaValoresFiltrados;
	}

	public void setListaValoresFiltrados(List<OcupacionHospitalariaBean> listaValoresFiltrados) {
		this.listaValoresFiltrados = listaValoresFiltrados;
	}
	
	
}
