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
import com.roberto.SCCyL.bean.SituacionResidenciasBean;

@Named
@ViewScoped
public class SituacionResidenciasView {

	@Autowired
	private CombosView combosView;

	private List<SituacionResidenciasBean> listaSituacionResidenciasGlobal;
	private List<SituacionResidenciasBean> listaSituacionResidenciasFiltrada;
	
	private boolean verListaFiltrada = false;
	
	private boolean verListaGlobal = true;
	
	/*LISTA PARA APLICAR FILTROS*/
	private List<String> listaProvinciasSeleccionadas;
	private Date fechaInicialFiltro;
	private Date fechaFinalFiltro;
	private Date fechaMaxima = new Date();
	private Date fechaMinima;
	private Date fechaHoy = new Date();
	
	@PostConstruct
	public void init() throws IOException {
		//Vuelvo a cargar la lista con los datos globales
		setListaSituacionResidenciasFiltrada(null);
		setListaSituacionResidenciasGlobal(null);
		
		//Recargo los booleanos a su valor inicial y pongo a null las listas y las fechas
		setVerListaFiltrada(false);
		setVerListaGlobal(true);
		setListaProvinciasSeleccionadas(null);
		setFechaInicialFiltro(null);
		setFechaFinalFiltro(null);
		setFechaMinima(null);
		setFechaMaxima(new Date());
	}

	public List<SituacionResidenciasBean> getListaSituacionResidenciasGlobal() throws IOException {
		if(listaSituacionResidenciasGlobal == null) {
			listaSituacionResidenciasGlobal = combosView.getListaSituacionResidencias();
			Comparator<SituacionResidenciasBean> comparator = Comparator.comparing(SituacionResidenciasBean::getFecha).reversed()
					.thenComparing(SituacionResidenciasBean::getProvincia);
			Collections.sort(listaSituacionResidenciasGlobal, comparator);
		}
		return listaSituacionResidenciasGlobal;
	}

	public void setListaSituacionResidenciasGlobal(List<SituacionResidenciasBean> listaSituacionResidenciasGlobal) {
		this.listaSituacionResidenciasGlobal = listaSituacionResidenciasGlobal;
	}

	public List<SituacionResidenciasBean> getListaSituacionResidenciasFiltrada() {
		if(listaSituacionResidenciasFiltrada == null) {
			listaSituacionResidenciasFiltrada = new ArrayList<SituacionResidenciasBean>();
		}
		return listaSituacionResidenciasFiltrada;
	}

	public void setListaSituacionResidenciasFiltrada(List<SituacionResidenciasBean> listaSituacionResidenciasFiltrada) {
		this.listaSituacionResidenciasFiltrada = listaSituacionResidenciasFiltrada;
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
