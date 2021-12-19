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

import com.roberto.SCCyL.bean.PersonasVacunadasBean;
import com.roberto.SCCyL.bean.PruebasRealizadasBean;

@Named
@ViewScoped
public class PersonasVacunadasView {

	@Autowired
	private CombosView combosView;

	private List<PersonasVacunadasBean> listaPersonasVacunadasGlobal;
	private List<PersonasVacunadasBean> listaPersonasVacunadasFiltrada;
	
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
		setListaPersonasVacunadasGlobal(null);
		setListaPersonasVacunadasFiltrada(null);
		
		//Recargo los booleanos a su valor inicial y pongo a null las listas y las fechas
		setVerListaFiltrada(false);
		setVerListaGlobal(true);
		setListaProvinciasSeleccionadas(null);
		setFechaInicialFiltro(null);
		setFechaFinalFiltro(null);
		setFechaMinima(null);
		setFechaMaxima(new Date());
	}

	public List<PersonasVacunadasBean> getListaPersonasVacunadasGlobal() throws IOException {
		if(listaPersonasVacunadasGlobal == null) {
			listaPersonasVacunadasGlobal = combosView.getListaPersonasVacunadas();
			Comparator<PersonasVacunadasBean> comparator = Comparator.comparing(PersonasVacunadasBean::getFecha).reversed()
					.thenComparing(PersonasVacunadasBean::getProvincia);
			Collections.sort(listaPersonasVacunadasGlobal, comparator);
		}
		return listaPersonasVacunadasGlobal;
	}

	public void setListaPersonasVacunadasGlobal(List<PersonasVacunadasBean> listaPersonasVacunadasGlobal) {
		this.listaPersonasVacunadasGlobal = listaPersonasVacunadasGlobal;
	}

	public List<PersonasVacunadasBean> getListaPersonasVacunadasFiltrada() {
		if(listaPersonasVacunadasFiltrada == null) {
			listaPersonasVacunadasFiltrada = new ArrayList<PersonasVacunadasBean>();
		}
		return listaPersonasVacunadasFiltrada;
	}

	public void setListaPersonasVacunadasFiltrada(List<PersonasVacunadasBean> listaPersonasVacunadasFiltrada) {
		this.listaPersonasVacunadasFiltrada = listaPersonasVacunadasFiltrada;
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
