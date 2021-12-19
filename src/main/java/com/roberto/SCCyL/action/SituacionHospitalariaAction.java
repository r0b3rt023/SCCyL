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

import com.roberto.SCCyL.bean.OcupacionHospitalariaBean;
import com.roberto.SCCyL.bean.SituacionHospitalariaBean;
import com.roberto.SCCyL.utils.ConstantesCSV;
import com.roberto.SCCyL.view.SituacionHospitalariaView;

@Named
@ViewScoped
public class SituacionHospitalariaAction {

	@Autowired
	private SituacionHospitalariaView sitHosView;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void aplicarFiltros() throws IOException, ParseException {
		if (sitHosView.isVerListaFiltrada() == false) {
			sitHosView.setVerListaFiltrada(!sitHosView.isVerListaFiltrada());
			sitHosView.setVerListaGlobal(!sitHosView.isVerListaGlobal());
		}
		sitHosView.setListaSituacionHospitalariaFiltrada(null);
		List<String> provinciasSeleccionadas = sitHosView.getListaProvinciasSeleccionadas();
		List<String> hospitalesSeleccionadas = sitHosView.getListaHospitalesSeleccionados();
		List<SituacionHospitalariaBean> listaSitHosFiltrada = sitHosView.getListaSituacionHospitalariaFiltrada();

		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_SITUACION_HOSPITALARIA;
		String line = "";
		String cvsSplitBy = ";";
		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));

		while ((line = br.readLine()) != null) {
			String[] datos = line.split(cvsSplitBy);
			// Creo el bean para cada uno de los hospitales
			SituacionHospitalariaBean sitHospBean = new SituacionHospitalariaBean();
			if (sitHosView.getFechaInicialFiltro() != null && sitHosView.getFechaFinalFiltro() != null) {
				if (!datos[0].equals("fecha")) {
					Date fechaDato = sdf.parse(datos[0].toString());
					if ((fechaDato.equals(sitHosView.getFechaInicialFiltro())
							|| fechaDato.after(sitHosView.getFechaInicialFiltro()))
							&& (fechaDato.equals(sitHosView.getFechaFinalFiltro())
									|| fechaDato.before(sitHosView.getFechaFinalFiltro()))) {
						if (hospitalesSeleccionadas.isEmpty() && provinciasSeleccionadas.isEmpty()) {
							sitHospBean = rellenarBean(datos);
							listaSitHosFiltrada.add(sitHospBean);
						} else {
							if (hospitalesSeleccionadas.contains(datos[1])
									|| provinciasSeleccionadas.contains(datos[2])) {
								sitHospBean = rellenarBean(datos);
								listaSitHosFiltrada.add(sitHospBean);
							}
						}
					}
				}
			} else {
				if (hospitalesSeleccionadas.isEmpty() && provinciasSeleccionadas.isEmpty()) {
					sitHospBean = rellenarBean(datos);
					listaSitHosFiltrada.add(sitHospBean);
				} else {
					if (hospitalesSeleccionadas.contains(datos[1]) || provinciasSeleccionadas.contains(datos[2])) {
						sitHospBean = rellenarBean(datos);
						listaSitHosFiltrada.add(sitHospBean);
					}
				}
			}

		}
		Comparator<SituacionHospitalariaBean> comparator = Comparator.comparing(SituacionHospitalariaBean::getFecha)
				.reversed().thenComparing(SituacionHospitalariaBean::getProvincia);
		Collections.sort(listaSitHosFiltrada, comparator);
		br.close();
		sitHosView.setListaSituacionHospitalariaFiltrada(listaSitHosFiltrada);
	}

	public void cancelarBusquedaFiltra() {
		sitHosView.setVerListaFiltrada(!sitHosView.isVerListaFiltrada());
		sitHosView.setVerListaGlobal(!sitHosView.isVerListaGlobal());
		sitHosView.setListaHospitalesSeleccionados(null);
		sitHosView.setListaProvinciasSeleccionadas(null);
		sitHosView.setListaSituacionHospitalariaFiltrada(null);
		sitHosView.setFechaFinalFiltro(null);
		sitHosView.setFechaInicialFiltro(null);
		sitHosView.setFechaMaxima(new Date());
	}

	public void cambiarFechaMaxima() {
		sitHosView.setFechaMaxima(sitHosView.getFechaFinalFiltro());
	}

	public void cambiarFechaMinima() {
		sitHosView.setFechaMinima(sitHosView.getFechaInicialFiltro());
	}

	public SituacionHospitalariaBean rellenarBean(String[] datos) {
		SituacionHospitalariaBean sitHospBean = new SituacionHospitalariaBean();
		sitHospBean.setFecha(datos[0]);
		sitHospBean.setHospital(datos[1]);
		sitHospBean.setProvincia(datos[2]);
		sitHospBean.setNuevosHospPlanta(datos[3]);
		sitHospBean.setHospPlanta(datos[4]);
		sitHospBean.setNuevosHospUCI(datos[5]);
		sitHospBean.setHospUCI(datos[6]);
		sitHospBean.setPorcentajeOcuUCI(datos[7]);
		sitHospBean.setNuevasAltas(datos[8]);
		sitHospBean.setAltas(datos[9]);
		sitHospBean.setNuevosFallecidos(datos[10]);
		sitHospBean.setFallecidos(datos[11]);
		return sitHospBean;
	}

}
