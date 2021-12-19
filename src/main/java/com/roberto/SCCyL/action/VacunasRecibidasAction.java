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

import com.roberto.SCCyL.bean.PersonasVacunadasBean;
import com.roberto.SCCyL.bean.VacunasRecibidasBean;
import com.roberto.SCCyL.utils.ConstantesCSV;
import com.roberto.SCCyL.view.VacunasRecibidasView;

@Named
@ViewScoped
public class VacunasRecibidasAction {

	@Autowired
	private VacunasRecibidasView vacunasRecibidasView;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void aplicarFiltros() throws IOException, ParseException {
		if (vacunasRecibidasView.isVerListaFiltrada() == false) {
			vacunasRecibidasView.setVerListaFiltrada(!vacunasRecibidasView.isVerListaFiltrada());
			vacunasRecibidasView.setVerListaGlobal(!vacunasRecibidasView.isVerListaGlobal());
		}
		vacunasRecibidasView.setListaVacunasRecibidasFiltrada(null);

		List<String> provinciasSeleccionadas = vacunasRecibidasView.getListaProvinciasSeleccionadas();
		List<VacunasRecibidasBean> listaVacunasRecibidasFiltrada = vacunasRecibidasView.getListaVacunasRecibidasFiltrada();
		
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_VACUNAS_RECIBIDAS;
		String line = "";
		String cvsSplitBy = ";";
		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));

		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if (saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				
				VacunasRecibidasBean vacunasRecibidasBean = new VacunasRecibidasBean();
				
				if (vacunasRecibidasView.getFechaInicialFiltro() != null && vacunasRecibidasView.getFechaFinalFiltro() != null) {
					Date fechaDato = sdf.parse(datos[0].toString());
					if ((fechaDato.equals(vacunasRecibidasView.getFechaInicialFiltro()) || fechaDato.after(vacunasRecibidasView.getFechaInicialFiltro()))
							&& (fechaDato.equals(vacunasRecibidasView.getFechaFinalFiltro()) || fechaDato.before(vacunasRecibidasView.getFechaFinalFiltro()))) {
						if (provinciasSeleccionadas.isEmpty()) {
							vacunasRecibidasBean = rellenarBean(datos);
							listaVacunasRecibidasFiltrada.add(vacunasRecibidasBean);
						} else {
							if (provinciasSeleccionadas.contains(datos[1])) {
								vacunasRecibidasBean = rellenarBean(datos);
								listaVacunasRecibidasFiltrada.add(vacunasRecibidasBean);
							}
						}
					}
				} else {
					if (provinciasSeleccionadas.isEmpty()) {
						vacunasRecibidasBean = rellenarBean(datos);
						listaVacunasRecibidasFiltrada.add(vacunasRecibidasBean);
					} else {
						if (provinciasSeleccionadas.contains(datos[1])) {
							vacunasRecibidasBean = rellenarBean(datos);
							listaVacunasRecibidasFiltrada.add(vacunasRecibidasBean);
						}
					}
				}
			}

		}
		Comparator<VacunasRecibidasBean> comparator = Comparator.comparing(VacunasRecibidasBean::getFecha)
				.reversed().thenComparing(VacunasRecibidasBean::getProvincia);
		Collections.sort(listaVacunasRecibidasFiltrada, comparator);
		br.close();
		vacunasRecibidasView.setListaVacunasRecibidasFiltrada(listaVacunasRecibidasFiltrada);
	}

	public void cancelarBusquedaFiltra() {
		vacunasRecibidasView.setVerListaFiltrada(!vacunasRecibidasView.isVerListaFiltrada());
		vacunasRecibidasView.setVerListaGlobal(!vacunasRecibidasView.isVerListaGlobal());
		vacunasRecibidasView.setListaProvinciasSeleccionadas(null);
		vacunasRecibidasView.setFechaFinalFiltro(null);
		vacunasRecibidasView.setFechaInicialFiltro(null);
		vacunasRecibidasView.setFechaMaxima(new Date());
	}

	public void cambiarFechaMaxima() {
		vacunasRecibidasView.setFechaMaxima(vacunasRecibidasView.getFechaFinalFiltro());
	}

	public void cambiarFechaMinima() {
		vacunasRecibidasView.setFechaMinima(vacunasRecibidasView.getFechaInicialFiltro());
	}

	public VacunasRecibidasBean rellenarBean(String[] datos) {
		VacunasRecibidasBean vacunasRecibidasBean = new VacunasRecibidasBean();
		vacunasRecibidasBean.setFecha(datos[0]);
		vacunasRecibidasBean.setProvincia(datos[1]);
		vacunasRecibidasBean.setVacunasRecibidas(datos[2]);
		return vacunasRecibidasBean;
	}
}
