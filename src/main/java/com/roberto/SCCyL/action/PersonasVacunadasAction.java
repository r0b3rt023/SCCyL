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
import com.roberto.SCCyL.utils.ConstantesCSV;
import com.roberto.SCCyL.view.PersonasVacunadasView;

@Named
@ViewScoped
public class PersonasVacunadasAction {

	@Autowired
	private PersonasVacunadasView personasVacunadasView;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void aplicarFiltros() throws IOException, ParseException {
		if (personasVacunadasView.isVerListaFiltrada() == false) {
			personasVacunadasView.setVerListaFiltrada(!personasVacunadasView.isVerListaFiltrada());
			personasVacunadasView.setVerListaGlobal(!personasVacunadasView.isVerListaGlobal());
		}
		personasVacunadasView.setListaPersonasVacunadasFiltrada(null);

		List<String> provinciasSeleccionadas = personasVacunadasView.getListaProvinciasSeleccionadas();
		List<PersonasVacunadasBean> listaPersonasVacunadasFiltrada = personasVacunadasView.getListaPersonasVacunadasFiltrada();
		
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_PERSONAS_VACUNADAS;
		String line = "";
		String cvsSplitBy = ";";
		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));

		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if (saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				
				PersonasVacunadasBean personasVacunadasBean = new PersonasVacunadasBean();
				
				if (personasVacunadasView.getFechaInicialFiltro() != null && personasVacunadasView.getFechaFinalFiltro() != null) {
					Date fechaDato = sdf.parse(datos[0].toString());
					if ((fechaDato.equals(personasVacunadasView.getFechaInicialFiltro()) || fechaDato.after(personasVacunadasView.getFechaInicialFiltro()))
							&& (fechaDato.equals(personasVacunadasView.getFechaFinalFiltro()) || fechaDato.before(personasVacunadasView.getFechaFinalFiltro()))) {
						if (provinciasSeleccionadas.isEmpty()) {
							personasVacunadasBean = rellenarBean(datos);
							listaPersonasVacunadasFiltrada.add(personasVacunadasBean);
						} else {
							if (provinciasSeleccionadas.contains(datos[1])) {
								personasVacunadasBean = rellenarBean(datos);
								listaPersonasVacunadasFiltrada.add(personasVacunadasBean);
							}
						}
					}
				} else {
					if (provinciasSeleccionadas.isEmpty()) {
						personasVacunadasBean = rellenarBean(datos);
						listaPersonasVacunadasFiltrada.add(personasVacunadasBean);
					} else {
						if (provinciasSeleccionadas.contains(datos[1])) {
							personasVacunadasBean = rellenarBean(datos);
							listaPersonasVacunadasFiltrada.add(personasVacunadasBean);
						}
					}
				}
			}

		}
		Comparator<PersonasVacunadasBean> comparator = Comparator.comparing(PersonasVacunadasBean::getFecha)
				.reversed().thenComparing(PersonasVacunadasBean::getProvincia);
		Collections.sort(listaPersonasVacunadasFiltrada, comparator);
		br.close();
		personasVacunadasView.setListaPersonasVacunadasFiltrada(listaPersonasVacunadasFiltrada);
	}

	public void cancelarBusquedaFiltra() {
		personasVacunadasView.setVerListaFiltrada(!personasVacunadasView.isVerListaFiltrada());
		personasVacunadasView.setVerListaGlobal(!personasVacunadasView.isVerListaGlobal());
		personasVacunadasView.setListaProvinciasSeleccionadas(null);
		personasVacunadasView.setFechaFinalFiltro(null);
		personasVacunadasView.setFechaInicialFiltro(null);
		personasVacunadasView.setFechaMaxima(new Date());
	}

	public void cambiarFechaMaxima() {
		personasVacunadasView.setFechaMaxima(personasVacunadasView.getFechaFinalFiltro());
	}

	public void cambiarFechaMinima() {
		personasVacunadasView.setFechaMinima(personasVacunadasView.getFechaInicialFiltro());
	}

	public PersonasVacunadasBean rellenarBean(String[] datos) {
		PersonasVacunadasBean personasVacunadasBean = new PersonasVacunadasBean();
		personasVacunadasBean.setFecha(datos[0]);
		personasVacunadasBean.setProvincia(datos[1]);
		personasVacunadasBean.setPersonasVacunadas(datos[2]);
		return personasVacunadasBean;
	}
}
