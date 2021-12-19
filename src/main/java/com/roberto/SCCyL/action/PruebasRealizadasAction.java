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

import com.roberto.SCCyL.bean.CentrosEducacionBean;
import com.roberto.SCCyL.bean.PruebasRealizadasBean;
import com.roberto.SCCyL.utils.ConstantesCSV;
import com.roberto.SCCyL.view.CentrosEducacionView;
import com.roberto.SCCyL.view.PruebasRealizadasView;

@Named
@ViewScoped
public class PruebasRealizadasAction {

	@Autowired
	private PruebasRealizadasView pruebasRealizadasView;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void aplicarFiltros() throws IOException, ParseException {
		if (pruebasRealizadasView.isVerListaFiltrada() == false) {
			pruebasRealizadasView.setVerListaFiltrada(!pruebasRealizadasView.isVerListaFiltrada());
			pruebasRealizadasView.setVerListaGlobal(!pruebasRealizadasView.isVerListaGlobal());
		}
		pruebasRealizadasView.setListaPruebasRealizadasFiltrada(null);

		List<String> provinciasSeleccionadas = pruebasRealizadasView.getListaProvinciasSeleccionadas();
		List<PruebasRealizadasBean> listaPruebasRealizadasFiltrada = pruebasRealizadasView.getListaPruebasRealizadasFiltrada();
		
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_PRUEBAS_REALIZADAS;
		String line = "";
		String cvsSplitBy = ";";
		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));

		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if (saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				
				PruebasRealizadasBean pruebasRealizadasBean = new PruebasRealizadasBean();
				
				if (pruebasRealizadasView.getFechaInicialFiltro() != null && pruebasRealizadasView.getFechaFinalFiltro() != null) {
					Date fechaDato = sdf.parse(datos[0].toString());
					if ((fechaDato.equals(pruebasRealizadasView.getFechaInicialFiltro()) || fechaDato.after(pruebasRealizadasView.getFechaInicialFiltro()))
							&& (fechaDato.equals(pruebasRealizadasView.getFechaFinalFiltro()) || fechaDato.before(pruebasRealizadasView.getFechaFinalFiltro()))) {
						if (provinciasSeleccionadas.isEmpty()) {
							pruebasRealizadasBean = rellenarBean(datos);
							listaPruebasRealizadasFiltrada.add(pruebasRealizadasBean);
						} else {
							if (provinciasSeleccionadas.contains(datos[1])) {
								pruebasRealizadasBean = rellenarBean(datos);
								listaPruebasRealizadasFiltrada.add(pruebasRealizadasBean);
							}
						}
					}
				} else {
					if (provinciasSeleccionadas.isEmpty()) {
						pruebasRealizadasBean = rellenarBean(datos);
						listaPruebasRealizadasFiltrada.add(pruebasRealizadasBean);
					} else {
						if (provinciasSeleccionadas.contains(datos[1])) {
							pruebasRealizadasBean = rellenarBean(datos);
							listaPruebasRealizadasFiltrada.add(pruebasRealizadasBean);
						}
					}
				}
			}

		}
		Comparator<PruebasRealizadasBean> comparator = Comparator.comparing(PruebasRealizadasBean::getFecha)
				.reversed().thenComparing(PruebasRealizadasBean::getProvincia);
		Collections.sort(listaPruebasRealizadasFiltrada, comparator);
		br.close();
		pruebasRealizadasView.setListaPruebasRealizadasFiltrada(listaPruebasRealizadasFiltrada);
	}

	public void cancelarBusquedaFiltra() {
		pruebasRealizadasView.setVerListaFiltrada(!pruebasRealizadasView.isVerListaFiltrada());
		pruebasRealizadasView.setVerListaGlobal(!pruebasRealizadasView.isVerListaGlobal());
		pruebasRealizadasView.setListaProvinciasSeleccionadas(null);
		pruebasRealizadasView.setFechaFinalFiltro(null);
		pruebasRealizadasView.setFechaInicialFiltro(null);
		pruebasRealizadasView.setFechaMaxima(new Date());
	}

	public void cambiarFechaMaxima() {
		pruebasRealizadasView.setFechaMaxima(pruebasRealizadasView.getFechaFinalFiltro());
	}

	public void cambiarFechaMinima() {
		pruebasRealizadasView.setFechaMinima(pruebasRealizadasView.getFechaInicialFiltro());
	}

	public PruebasRealizadasBean rellenarBean(String[] datos) {
		PruebasRealizadasBean pruebasRealizadasBean = new PruebasRealizadasBean();
		pruebasRealizadasBean.setFecha(datos[0]);
		pruebasRealizadasBean.setProvincia(datos[1]);
		pruebasRealizadasBean.setTestRapidosTotales(datos[2]);
		pruebasRealizadasBean.setTestRapidosPositivos(datos[3]);
		pruebasRealizadasBean.setTestAntiTotales(datos[4]);
		pruebasRealizadasBean.setTestAntiPositivos(datos[5]);
		pruebasRealizadasBean.setPcrTotal(datos[6]);
		pruebasRealizadasBean.setPcrPositivos(datos[7]);
		pruebasRealizadasBean.setTotalPruebas(datos[9]);
		pruebasRealizadasBean.setTotalPruebasPositivas(datos[10]);
		return pruebasRealizadasBean;
	}
}
