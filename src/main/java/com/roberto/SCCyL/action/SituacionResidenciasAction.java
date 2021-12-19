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

import com.roberto.SCCyL.bean.SituacionResidenciasBean;
import com.roberto.SCCyL.utils.ConstantesCSV;
import com.roberto.SCCyL.view.SituacionResidenciasView;

@Named
@ViewScoped
public class SituacionResidenciasAction {

	@Autowired
	private SituacionResidenciasView situacionResView;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void aplicarFiltros() throws IOException, ParseException {
		if (situacionResView.isVerListaFiltrada() == false) {
			situacionResView.setVerListaFiltrada(!situacionResView.isVerListaFiltrada());
			situacionResView.setVerListaGlobal(!situacionResView.isVerListaGlobal());
		}
		situacionResView.setListaSituacionResidenciasFiltrada(null);

		List<String> provinciasSeleccionadas = situacionResView.getListaProvinciasSeleccionadas();
		List<SituacionResidenciasBean> listaSituacionResFiltrada = situacionResView
				.getListaSituacionResidenciasFiltrada();

		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_SITUACION_RESIDENCIAS;
		String line = "";
		String cvsSplitBy = ";";
		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));

		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if (saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				// Creo el bean para cada uno de los hospitales
				SituacionResidenciasBean situacionResBean = new SituacionResidenciasBean();
				if (situacionResView.getFechaInicialFiltro() != null && situacionResView.getFechaFinalFiltro() != null) {
					Date fechaDato = sdf.parse(datos[0].toString());
					if ((fechaDato.equals(situacionResView.getFechaInicialFiltro()) || fechaDato.after(situacionResView.getFechaInicialFiltro()))
							&& (fechaDato.equals(situacionResView.getFechaFinalFiltro()) || fechaDato.before(situacionResView.getFechaFinalFiltro()))) {
						if (provinciasSeleccionadas.isEmpty()) {
							situacionResBean = rellenarBean(datos);
							listaSituacionResFiltrada.add(situacionResBean);
						} else {
							if (provinciasSeleccionadas.contains(datos[1])) {
								situacionResBean = rellenarBean(datos);
								listaSituacionResFiltrada.add(situacionResBean);
							}
						}
					}
				} else {
					if (provinciasSeleccionadas.isEmpty()) {
						situacionResBean = rellenarBean(datos);
						listaSituacionResFiltrada.add(situacionResBean);
					} else {
						if (provinciasSeleccionadas.contains(datos[1])) {
							situacionResBean = rellenarBean(datos);
							listaSituacionResFiltrada.add(situacionResBean);
						}
					}
				}
			}

		}
		Comparator<SituacionResidenciasBean> comparator = Comparator.comparing(SituacionResidenciasBean::getFecha)
				.reversed().thenComparing(SituacionResidenciasBean::getProvincia);
		Collections.sort(listaSituacionResFiltrada, comparator);
		br.close();
		situacionResView.setListaSituacionResidenciasFiltrada(listaSituacionResFiltrada);
	}

	public void cancelarBusquedaFiltra() {
		situacionResView.setVerListaFiltrada(!situacionResView.isVerListaFiltrada());
		situacionResView.setVerListaGlobal(!situacionResView.isVerListaGlobal());
		situacionResView.setListaProvinciasSeleccionadas(null);
		situacionResView.setFechaFinalFiltro(null);
		situacionResView.setFechaInicialFiltro(null);
		situacionResView.setFechaMaxima(new Date());
	}

	public void cambiarFechaMaxima() {
		situacionResView.setFechaMaxima(situacionResView.getFechaFinalFiltro());
	}

	public void cambiarFechaMinima() {
		situacionResView.setFechaMinima(situacionResView.getFechaInicialFiltro());
	}

	public SituacionResidenciasBean rellenarBean(String[] datos) {
		SituacionResidenciasBean situacionResBean = new SituacionResidenciasBean();
		situacionResBean.setFecha(datos[0]);
		situacionResBean.setProvincia(datos[1]);
		situacionResBean.setPersonasEnResidencias(datos[2]);
		situacionResBean.setMortalidad(datos[3]);
		situacionResBean.setFallecidosCOVID(datos[4]);
		situacionResBean.setFallecidosSintomas(datos[5]);
		situacionResBean.setConfirmadosCOVID(datos[6]);
		situacionResBean.setAisladosConSintomas(datos[7]);
		situacionResBean.setAisladosSinSintomas(datos[8]);
		return situacionResBean;
	}
}
