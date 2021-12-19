package com.roberto.SCCyL.action;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.roberto.SCCyL.bean.SanitariosAfectadosBean;
import com.roberto.SCCyL.bean.VacunasRecibidasBean;
import com.roberto.SCCyL.utils.ConstantesCSV;
import com.roberto.SCCyL.view.SanitariosAfectadosView;
import com.roberto.SCCyL.view.VacunasRecibidasView;

@Named
@ViewScoped
public class SanitariosAfectadosAction {

	@Autowired
	private SanitariosAfectadosView sanitariosAfectadosView;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void aplicarFiltros() throws IOException, ParseException {
		if (sanitariosAfectadosView.isVerListaFiltrada() == false) {
			sanitariosAfectadosView.setVerListaFiltrada(!sanitariosAfectadosView.isVerListaFiltrada());
			sanitariosAfectadosView.setVerListaGlobal(!sanitariosAfectadosView.isVerListaGlobal());
		}
		sanitariosAfectadosView.setListaSanitariosAfectadosFiltrada(null);

		List<String> provinciasSeleccionadas = sanitariosAfectadosView.getListaProvinciasSeleccionadas();
		List<String> ambitosSeleccionados = sanitariosAfectadosView.getListaAmbitosSeleccionados();
		List<String> categoriasSeleccionadas = sanitariosAfectadosView.getListaCategoriasSeleccionadas();
		List<SanitariosAfectadosBean> listaSanitariosAfectadosFiltrada = sanitariosAfectadosView.getListaSanitariosAfectadosFiltrada();
		
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_SANITARIOS_AFECTADOS;
		String line = "";
		String cvsSplitBy = ";";
		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));

		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if (saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				
				SanitariosAfectadosBean sanitariosAfectadosBean = new SanitariosAfectadosBean();
				
				if (sanitariosAfectadosView.getFechaInicialFiltro() != null && sanitariosAfectadosView.getFechaFinalFiltro() != null) {
					Date fechaDato = sdf.parse(datos[0].toString());
					if ((fechaDato.equals(sanitariosAfectadosView.getFechaInicialFiltro()) || fechaDato.after(sanitariosAfectadosView.getFechaInicialFiltro()))
							&& (fechaDato.equals(sanitariosAfectadosView.getFechaFinalFiltro()) || fechaDato.before(sanitariosAfectadosView.getFechaFinalFiltro()))) {
						if (provinciasSeleccionadas.isEmpty() && ambitosSeleccionados.isEmpty() && categoriasSeleccionadas.isEmpty()) {
							sanitariosAfectadosBean = rellenarBean(datos);
							listaSanitariosAfectadosFiltrada.add(sanitariosAfectadosBean);
						} else {
							if (provinciasSeleccionadas.contains(datos[1]) || ambitosSeleccionados.contains(datos[2])
									|| categoriasSeleccionadas.contains(datos[3])) {
								sanitariosAfectadosBean = rellenarBean(datos);
								listaSanitariosAfectadosFiltrada.add(sanitariosAfectadosBean);
							}
						}
					}
				} else {
					if (provinciasSeleccionadas.isEmpty()) {
						sanitariosAfectadosBean = rellenarBean(datos);
						listaSanitariosAfectadosFiltrada.add(sanitariosAfectadosBean);
					} else {
						if (provinciasSeleccionadas.contains(datos[1])) {
							sanitariosAfectadosBean = rellenarBean(datos);
							listaSanitariosAfectadosFiltrada.add(sanitariosAfectadosBean);
						}
					}
				}
			}

		}
		Comparator<SanitariosAfectadosBean> comparator = Comparator.comparing(SanitariosAfectadosBean::getFecha)
				.reversed().thenComparing(SanitariosAfectadosBean::getProvincia);
		Collections.sort(listaSanitariosAfectadosFiltrada, comparator);
		br.close();
		sanitariosAfectadosView.setListaSanitariosAfectadosFiltrada(listaSanitariosAfectadosFiltrada);
	}

	public void cancelarBusquedaFiltra() {
		sanitariosAfectadosView.setVerListaFiltrada(!sanitariosAfectadosView.isVerListaFiltrada());
		sanitariosAfectadosView.setVerListaGlobal(!sanitariosAfectadosView.isVerListaGlobal());
		sanitariosAfectadosView.setListaProvinciasSeleccionadas(null);
		sanitariosAfectadosView.setFechaFinalFiltro(null);
		sanitariosAfectadosView.setFechaInicialFiltro(null);
		sanitariosAfectadosView.setFechaMaxima(new Date());
	}

	public void cambiarFechaMaxima() {
		sanitariosAfectadosView.setFechaMaxima(sanitariosAfectadosView.getFechaFinalFiltro());
	}

	public void cambiarFechaMinima() {
		sanitariosAfectadosView.setFechaMinima(sanitariosAfectadosView.getFechaInicialFiltro());
	}

	public SanitariosAfectadosBean rellenarBean(String[] datos) {
		SanitariosAfectadosBean sanitariosBean = new SanitariosAfectadosBean();
		sanitariosBean.setFecha(datos[0]);
		sanitariosBean.setProvincia(datos[1]);
		sanitariosBean.setAmbito(datos[2]);
		sanitariosBean.setCategoria(datos[3]);
		sanitariosBean.setTestRealizados(datos[4]);
		sanitariosBean.setTestPositivos(datos[5]);
		sanitariosBean.setAislamiento(datos[6]);
		sanitariosBean.setAlta(datos[7]);
		return sanitariosBean;
	}
}
