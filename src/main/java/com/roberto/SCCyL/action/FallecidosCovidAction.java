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

import com.roberto.SCCyL.bean.FallecidosCovidBean;
import com.roberto.SCCyL.bean.PersonasVacunadasBean;
import com.roberto.SCCyL.utils.ConstantesCSV;
import com.roberto.SCCyL.view.FallecidosCovidView;

@Named
@ViewScoped
public class FallecidosCovidAction {

	@Autowired
	private FallecidosCovidView fallecidosCovidView;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void aplicarFiltros() throws IOException, ParseException {
		if (fallecidosCovidView.isVerListaFiltrada() == false) {
			fallecidosCovidView.setVerListaFiltrada(!fallecidosCovidView.isVerListaFiltrada());
			fallecidosCovidView.setVerListaGlobal(!fallecidosCovidView.isVerListaGlobal());
		}
		fallecidosCovidView.setListaFallecidosCovidFiltrada(null);

		List<String> provinciasSeleccionadas = fallecidosCovidView.getListaProvinciasSeleccionadas();
		List<String> gerenciasSeleccionadas = fallecidosCovidView.getListaGerenciasSeleccionadas();
		List<String> centrosSanitariosSeleccionados = fallecidosCovidView.getListaCentrosSanitariosSeleccionados();
		List<FallecidosCovidBean> listaPersonasVacunadasFiltrada = fallecidosCovidView.getListaFallecidosCovidFiltrada();
		
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_FALLECIDOS_COVID;
		String line = "";
		String cvsSplitBy = ";";
		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));

		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if (saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				
				FallecidosCovidBean fallecidosCovidBean = new FallecidosCovidBean();
				
				if (fallecidosCovidView.getFechaInicialFiltro() != null && fallecidosCovidView.getFechaFinalFiltro() != null) {
					Date fechaDato = sdf.parse(datos[0].toString());
					if ((fechaDato.equals(fallecidosCovidView.getFechaInicialFiltro()) || fechaDato.after(fallecidosCovidView.getFechaInicialFiltro()))
							&& (fechaDato.equals(fallecidosCovidView.getFechaFinalFiltro()) || fechaDato.before(fallecidosCovidView.getFechaFinalFiltro()))) {
						if (provinciasSeleccionadas.isEmpty() && gerenciasSeleccionadas.isEmpty() &&
								centrosSanitariosSeleccionados.isEmpty()) {
							fallecidosCovidBean = rellenarBean(datos);
							listaPersonasVacunadasFiltrada.add(fallecidosCovidBean);
						} else {
							if (provinciasSeleccionadas.contains(datos[10]) || gerenciasSeleccionadas.contains(datos[2]) ||
									centrosSanitariosSeleccionados.contains(datos[4])) {
								fallecidosCovidBean = rellenarBean(datos);
								listaPersonasVacunadasFiltrada.add(fallecidosCovidBean);
							}
						}
					}
				} else {
					if (provinciasSeleccionadas.isEmpty() && gerenciasSeleccionadas.isEmpty() &&
							centrosSanitariosSeleccionados.isEmpty()) {
						fallecidosCovidBean = rellenarBean(datos);
						listaPersonasVacunadasFiltrada.add(fallecidosCovidBean);
					} else {
						if (provinciasSeleccionadas.contains(datos[10]) || gerenciasSeleccionadas.contains(datos[2]) ||
								centrosSanitariosSeleccionados.contains(datos[4])) {
							fallecidosCovidBean = rellenarBean(datos);
							listaPersonasVacunadasFiltrada.add(fallecidosCovidBean);
						}
					}
				}
			}

		}
		Comparator<FallecidosCovidBean> comparator = Comparator.comparing(FallecidosCovidBean::getFecha)
				.reversed().thenComparing(FallecidosCovidBean::getProvincia);
		Collections.sort(listaPersonasVacunadasFiltrada, comparator);
		br.close();
		fallecidosCovidView.setListaFallecidosCovidFiltrada(listaPersonasVacunadasFiltrada);
	}

	public void cancelarBusquedaFiltra() {
		fallecidosCovidView.setVerListaFiltrada(!fallecidosCovidView.isVerListaFiltrada());
		fallecidosCovidView.setVerListaGlobal(!fallecidosCovidView.isVerListaGlobal());
		fallecidosCovidView.setListaProvinciasSeleccionadas(null);
		fallecidosCovidView.setFechaFinalFiltro(null);
		fallecidosCovidView.setFechaInicialFiltro(null);
		fallecidosCovidView.setFechaMaxima(new Date());
	}

	public void cambiarFechaMaxima() {
		fallecidosCovidView.setFechaMaxima(fallecidosCovidView.getFechaFinalFiltro());
	}

	public void cambiarFechaMinima() {
		fallecidosCovidView.setFechaMinima(fallecidosCovidView.getFechaInicialFiltro());
	}

	public FallecidosCovidBean rellenarBean(String[] datos) {
		FallecidosCovidBean fallecidosCovidBean = new FallecidosCovidBean();
		fallecidosCovidBean.setFecha(datos[0]);
		fallecidosCovidBean.setProvincia(datos[10]);
		fallecidosCovidBean.setGerencia(datos[2]);
		fallecidosCovidBean.setCentro(datos[4]);
		fallecidosCovidBean.setFallecidos(datos[5]);
		return fallecidosCovidBean;
	}
}
