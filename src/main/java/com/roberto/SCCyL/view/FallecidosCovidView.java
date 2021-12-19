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

import com.roberto.SCCyL.bean.FallecidosCovidBean;
import com.roberto.SCCyL.bean.PersonasVacunadasBean;

@Named
@ViewScoped
public class FallecidosCovidView {

	@Autowired
	private CombosView combosView;

	private List<FallecidosCovidBean> listaFallecidosCovidGlobal;
	private List<FallecidosCovidBean> listaFallecidosCovidFiltrada;
	
	private boolean verListaFiltrada = false;
	
	private boolean verListaGlobal = true;
	
	/*LISTA PARA APLICAR FILTROS*/
	private List<String> listaProvinciasSeleccionadas;
	private List<String> listaGerenciasSeleccionadas;
	private List<String> listaCentrosSanitariosSeleccionados;
	private Date fechaInicialFiltro;
	private Date fechaFinalFiltro;
	private Date fechaMaxima = new Date();
	private Date fechaMinima;
	private Date fechaHoy = new Date();
	
	@PostConstruct
	public void init() throws IOException {
		//Vuelvo a cargar la lista con los datos globales
		setListaFallecidosCovidGlobal(null);
		setListaFallecidosCovidFiltrada(null);
		
		//Recargo los booleanos a su valor inicial y pongo a null las listas y las fechas
		setVerListaFiltrada(false);
		setVerListaGlobal(true);
		setListaProvinciasSeleccionadas(null);
		setListaCentrosSanitariosSeleccionados(null);
		setListaGerenciasSeleccionadas(null);
		setFechaInicialFiltro(null);
		setFechaFinalFiltro(null);
		setFechaMinima(null);
		setFechaMaxima(new Date());
	}

	public List<FallecidosCovidBean> getListaFallecidosCovidGlobal() throws IOException {
		if(listaFallecidosCovidGlobal == null) {
			listaFallecidosCovidGlobal = combosView.getListaFallecidosCovid();
			Comparator<FallecidosCovidBean> comparator = Comparator.comparing(FallecidosCovidBean::getFecha).reversed()
					.thenComparing(FallecidosCovidBean::getProvincia);
			Collections.sort(listaFallecidosCovidGlobal, comparator);
		}
		return listaFallecidosCovidGlobal;
	}

	public void setListaFallecidosCovidGlobal(List<FallecidosCovidBean> listaFallecidosCovidGlobal) {
		this.listaFallecidosCovidGlobal = listaFallecidosCovidGlobal;
	}

	public List<FallecidosCovidBean> getListaFallecidosCovidFiltrada() {
		if(listaFallecidosCovidFiltrada == null) {
			listaFallecidosCovidFiltrada = new ArrayList<FallecidosCovidBean>();
		}
		return listaFallecidosCovidFiltrada;
	}

	public void setListaFallecidosCovidFiltrada(List<FallecidosCovidBean> listaFallecidosCovidFiltrada) {
		this.listaFallecidosCovidFiltrada = listaFallecidosCovidFiltrada;
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

	public List<String> getListaGerenciasSeleccionadas() {
		return listaGerenciasSeleccionadas;
	}

	public void setListaGerenciasSeleccionadas(List<String> listaGerenciasSeleccionadas) {
		this.listaGerenciasSeleccionadas = listaGerenciasSeleccionadas;
	}

	public List<String> getListaCentrosSanitariosSeleccionados() {
		return listaCentrosSanitariosSeleccionados;
	}

	public void setListaCentrosSanitariosSeleccionados(List<String> listaCentrosSanitariosSeleccionados) {
		this.listaCentrosSanitariosSeleccionados = listaCentrosSanitariosSeleccionados;
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
