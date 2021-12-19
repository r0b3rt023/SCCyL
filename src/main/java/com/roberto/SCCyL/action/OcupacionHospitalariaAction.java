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
import com.roberto.SCCyL.utils.ConstantesCSV;
import com.roberto.SCCyL.view.OcupacionHospitalariaView;

@Named
@ViewScoped
public class OcupacionHospitalariaAction {

	@Autowired
	private OcupacionHospitalariaView ocuHosView;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void aplicarFiltros() throws IOException, ParseException {
		if(ocuHosView.isVerListaFiltrada() == false) {
			ocuHosView.setVerListaFiltrada(!ocuHosView.isVerListaFiltrada());
			ocuHosView.setVerListaGlobal(!ocuHosView.isVerListaGlobal());
		}
		ocuHosView.setListaOcupacionHospitalariaFiltrada(null);
		
		List<String> provinciasSeleccionadas = ocuHosView.getListaProvinciasSeleccionadas();
		List<String> hospitalesSeleccionadas = ocuHosView.getListaHospitalesSeleccionados();
		List<OcupacionHospitalariaBean> listaOcuHosFiltrada = ocuHosView.getListaOcupacionHospitalariaFiltrada();
		
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_OCUPACION_HOSPITALARIA;
		String line = "";
		String cvsSplitBy = ";";
		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));
		
		while ((line = br.readLine()) != null) {
			String[] datos = line.split(cvsSplitBy);
			// Creo el bean para cada uno de los hospitales
			OcupacionHospitalariaBean ocuHospBean = new OcupacionHospitalariaBean();
			if(ocuHosView.getFechaInicialFiltro() != null && ocuHosView.getFechaFinalFiltro() != null) {
				if(!datos[0].equals("fecha")) {
					Date fechaDato = sdf.parse(datos[0].toString());
					if((fechaDato.equals(ocuHosView.getFechaInicialFiltro()) || fechaDato.after(ocuHosView.getFechaInicialFiltro())) && 
							(fechaDato.equals(ocuHosView.getFechaFinalFiltro()) || fechaDato.before(ocuHosView.getFechaFinalFiltro()))) {
						if(hospitalesSeleccionadas.isEmpty() && provinciasSeleccionadas.isEmpty()) {
							ocuHospBean = rellenarBean(datos);
							listaOcuHosFiltrada.add(ocuHospBean);
						} else {
							if(hospitalesSeleccionadas.contains(datos[1]) || provinciasSeleccionadas.contains(datos[2])) {
								ocuHospBean = rellenarBean(datos);
								listaOcuHosFiltrada.add(ocuHospBean);
							}
						}
					}
				}
			} else {
				if(hospitalesSeleccionadas.isEmpty() && provinciasSeleccionadas.isEmpty()) {
					ocuHospBean = rellenarBean(datos);
					listaOcuHosFiltrada.add(ocuHospBean);
				} else {
					if(hospitalesSeleccionadas.contains(datos[1]) || provinciasSeleccionadas.contains(datos[2])) {
						ocuHospBean = rellenarBean(datos);
						listaOcuHosFiltrada.add(ocuHospBean);
					}
				}
			}
			
		}
		Comparator<OcupacionHospitalariaBean> comparator = Comparator.comparing(OcupacionHospitalariaBean::getFecha).reversed()
				.thenComparing(OcupacionHospitalariaBean::getProvincia);
		Collections.sort(listaOcuHosFiltrada, comparator);
		br.close();
		ocuHosView.setListaOcupacionHospitalariaFiltrada(listaOcuHosFiltrada);
	}
	
	public void cancelarBusquedaFiltra() {
		ocuHosView.setVerListaFiltrada(!ocuHosView.isVerListaFiltrada());
		ocuHosView.setVerListaGlobal(!ocuHosView.isVerListaGlobal());
		ocuHosView.setListaHospitalesSeleccionados(null);
		ocuHosView.setListaProvinciasSeleccionadas(null);
		ocuHosView.setListaOcupacionHospitalariaFiltrada(null);
		ocuHosView.setFechaFinalFiltro(null);
		ocuHosView.setFechaInicialFiltro(null);
		ocuHosView.setFechaMaxima(new Date());
	}
	
	public void cambiarFechaMaxima() {
		ocuHosView.setFechaMaxima(ocuHosView.getFechaFinalFiltro());
	}
	
	public void cambiarFechaMinima() {
		ocuHosView.setFechaMinima(ocuHosView.getFechaInicialFiltro());
	}

	public OcupacionHospitalariaBean rellenarBean(String[] datos) {
		OcupacionHospitalariaBean ocuHospBean = new OcupacionHospitalariaBean();
		ocuHospBean.setFecha(datos[0]);
		ocuHospBean.setHospital(datos[1]);
		ocuHospBean.setHospital(datos[1]);
		ocuHospBean.setProvincia(datos[2]);
		ocuHospBean.setCamasIniPlanta(datos[3]);
		ocuHospBean.setCamasHabPlanta(datos[4]);
		ocuHospBean.setCamasOcuPlanta(datos[5]);
		ocuHospBean.setCamasIniUCI(datos[6]);
		ocuHospBean.setCamasHabUCI(datos[7]);
		ocuHospBean.setCamasOcuUCI(datos[8]);
		ocuHospBean.setCamasOcuCovidUCI(datos[9]);
		return ocuHospBean;
	}
}
