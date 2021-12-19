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
import com.roberto.SCCyL.utils.ConstantesCSV;
import com.roberto.SCCyL.view.CentrosEducacionView;

@Named
@ViewScoped
public class CentrosEducacionAction {

	@Autowired
	private CentrosEducacionView centrosEduView;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void aplicarFiltros() throws IOException, ParseException {
		if (centrosEduView.isVerListaFiltrada() == false) {
			centrosEduView.setVerListaFiltrada(!centrosEduView.isVerListaFiltrada());
			centrosEduView.setVerListaGlobal(!centrosEduView.isVerListaGlobal());
		}
		centrosEduView.setListaCentrosEducacionFiltrada(null);

		List<String> provinciasSeleccionadas = centrosEduView.getListaProvinciasSeleccionadas();
		List<CentrosEducacionBean> listaCentrosEduFiltrada = centrosEduView.getListaCentrosEducacionFiltrada();
		
		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_CENTROS_EDUCACION;
		String line = "";
		String cvsSplitBy = ";";
		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));

		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if (saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				
				CentrosEducacionBean centrosEduBean = new CentrosEducacionBean();
				
				if (centrosEduView.getFechaInicialFiltro() != null && centrosEduView.getFechaFinalFiltro() != null) {
					Date fechaDato = sdf.parse(datos[0].toString());
					if ((fechaDato.equals(centrosEduView.getFechaInicialFiltro()) || fechaDato.after(centrosEduView.getFechaInicialFiltro()))
							&& (fechaDato.equals(centrosEduView.getFechaFinalFiltro()) || fechaDato.before(centrosEduView.getFechaFinalFiltro()))) {
						if (provinciasSeleccionadas.isEmpty()) {
							centrosEduBean = rellenarBean(datos);
							listaCentrosEduFiltrada.add(centrosEduBean);
						} else {
							if (provinciasSeleccionadas.contains(datos[1])) {
								centrosEduBean = rellenarBean(datos);
								listaCentrosEduFiltrada.add(centrosEduBean);
							}
						}
					}
				} else {
					if (provinciasSeleccionadas.isEmpty()) {
						centrosEduBean = rellenarBean(datos);
						listaCentrosEduFiltrada.add(centrosEduBean);
					} else {
						if (provinciasSeleccionadas.contains(datos[2])) {
							centrosEduBean = rellenarBean(datos);
							listaCentrosEduFiltrada.add(centrosEduBean);
						}
					}
				}
			}

		}
		Comparator<CentrosEducacionBean> comparator = Comparator.comparing(CentrosEducacionBean::getFecha)
				.reversed().thenComparing(CentrosEducacionBean::getProvincia);
		Collections.sort(listaCentrosEduFiltrada, comparator);
		br.close();
		centrosEduView.setListaCentrosEducacionFiltrada(listaCentrosEduFiltrada);
	}

	public void cancelarBusquedaFiltra() {
		centrosEduView.setVerListaFiltrada(!centrosEduView.isVerListaFiltrada());
		centrosEduView.setVerListaGlobal(!centrosEduView.isVerListaGlobal());
		centrosEduView.setListaProvinciasSeleccionadas(null);
		centrosEduView.setFechaFinalFiltro(null);
		centrosEduView.setFechaInicialFiltro(null);
		centrosEduView.setFechaMaxima(new Date());
	}

	public void cambiarFechaMaxima() {
		centrosEduView.setFechaMaxima(centrosEduView.getFechaFinalFiltro());
	}

	public void cambiarFechaMinima() {
		centrosEduView.setFechaMinima(centrosEduView.getFechaInicialFiltro());
	}

	public CentrosEducacionBean rellenarBean(String[] datos) {
		CentrosEducacionBean centrosEduBean = new CentrosEducacionBean();
		centrosEduBean.setFecha(datos[0]);
		centrosEduBean.setProvincia(datos[1]);
		centrosEduBean.setNuevosPosAlum(datos[3]);
		centrosEduBean.setNuevosPosProf(datos[4]);
		centrosEduBean.setAcumuladosAlum(datos[5]);
		centrosEduBean.setAcumuladosProf(datos[6]);
		centrosEduBean.setNuevasAulasCuaren(datos[7]);
		centrosEduBean.setTotalAulasCuarenActiva(datos[8]);
		centrosEduBean.setCentrosCuarentena(datos[9]);
		return centrosEduBean;
	}
}
