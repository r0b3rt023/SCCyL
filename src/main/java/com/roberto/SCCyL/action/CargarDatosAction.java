package com.roberto.SCCyL.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.roberto.SCCyL.bean.CentrosEducacionBean;
import com.roberto.SCCyL.bean.FallecidosCovidBean;
import com.roberto.SCCyL.bean.OcupacionHospitalariaBean;
import com.roberto.SCCyL.bean.PersonasVacunadasBean;
import com.roberto.SCCyL.bean.PruebasRealizadasBean;
import com.roberto.SCCyL.bean.SanitariosAfectadosBean;
import com.roberto.SCCyL.bean.SituacionHospitalariaBean;
import com.roberto.SCCyL.bean.SituacionResidenciasBean;
import com.roberto.SCCyL.bean.VacunasRecibidasBean;
import com.roberto.SCCyL.utils.ConstantesCSV;
import com.roberto.SCCyL.view.DatosGeneralesView;

@Named
@ApplicationScoped
public class CargarDatosAction {

	@Autowired
	private DatosGeneralesView datosGeneralesView = new DatosGeneralesView();

	private List<String> listaHospitales = datosGeneralesView.getListaDeHospitales();
	private List<String> listaProvincias = datosGeneralesView.getListaDeProvincias();
	private List<String> listaAmbitoSanitario = datosGeneralesView.getListaAmbitoSanitario();
	private List<String> listaCategoriaSanitario = datosGeneralesView.getListaCategoriaSanitario();
	private List<String> listaCentrosSanitarios = datosGeneralesView.getListaCentrosSanitarios();
	private List<String> listaGerencias = datosGeneralesView.getListaGerencias();
	private DescargarFicherosAction descargarFicheros = new DescargarFicherosAction();

	public List<OcupacionHospitalariaBean> cargarOcupacionHospitalaria() throws IOException {

		List<OcupacionHospitalariaBean> listaOcuHosp = new ArrayList<OcupacionHospitalariaBean>();

		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_OCUPACION_HOSPITALARIA;
//		descargarFicheros.descargarFicheroOcupacionHospitalaria();

		String line = "";
		String cvsSplitBy = ";";

		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));
		
		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if(saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				// Creo el bean para cada uno de los hospitales
				OcupacionHospitalariaBean ocuHospBean = new OcupacionHospitalariaBean();
				ocuHospBean.setFecha(datos[0]);
				ocuHospBean.setHospital(datos[1]);
				ocuHospBean.setProvincia(datos[2]);
				ocuHospBean.setCamasIniPlanta(datos[3]);
				ocuHospBean.setCamasHabPlanta(datos[4]);
				ocuHospBean.setCamasOcuPlanta(datos[5]);
				ocuHospBean.setCamasIniUCI(datos[6]);
				ocuHospBean.setCamasHabUCI(datos[7]);
				ocuHospBean.setCamasOcuUCI(datos[8]);
				ocuHospBean.setCamasOcuCovidUCI(datos[9]);
				// Lo añado a la lista
				listaOcuHosp.add(ocuHospBean);
				//Cargo los datos a las listas globales
				cargarListaHospitales(datos[1]);
				cargarListaProvincias(datos[2]);
			}
		}
		Comparator<OcupacionHospitalariaBean> comparator = Comparator.comparing(OcupacionHospitalariaBean::getFecha)
				.reversed().thenComparing(OcupacionHospitalariaBean::getProvincia);
		Collections.sort(listaOcuHosp, comparator);
		br.close();
		return listaOcuHosp;
	}

	public List<SituacionHospitalariaBean> cargarSituacionHospitalaria() throws IOException {

		List<SituacionHospitalariaBean> listaSituHosp = new ArrayList<SituacionHospitalariaBean>();

		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_SITUACION_HOSPITALARIA;
//		descargarFicheros.descargarFicheroSituacionHospitalaria();

		String line = "";
		String cvsSplitBy = ";";

		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));
		
		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if(saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				// Creo el bean para cada uno de los hospitales
				SituacionHospitalariaBean situHospBean = new SituacionHospitalariaBean();
				situHospBean.setFecha(datos[0]);
				situHospBean.setHospital(datos[1]);
				situHospBean.setProvincia(datos[2]);
				situHospBean.setNuevosHospPlanta(datos[3]);
				situHospBean.setHospPlanta(datos[4]);
				situHospBean.setNuevosHospUCI(datos[6]);
				situHospBean.setHospUCI(datos[7]);
				situHospBean.setPorcentajeOcuUCI(datos[9]);
				situHospBean.setNuevasAltas(datos[10]);
				situHospBean.setAltas(datos[11]);
				situHospBean.setFallecidos(datos[12]);
				situHospBean.setNuevosFallecidos(datos[13]);
				// Lo añado a la lista
				listaSituHosp.add(situHospBean);
				//Cargo los datos a las listas globales
				cargarListaHospitales(datos[1]);
				cargarListaProvincias(datos[2]);
			}
		}
		Comparator<SituacionHospitalariaBean> comparator = Comparator.comparing(SituacionHospitalariaBean::getFecha)
				.reversed().thenComparing(SituacionHospitalariaBean::getProvincia);
		Collections.sort(listaSituHosp, comparator);
		br.close();

		return listaSituHosp;
	}

	public List<SanitariosAfectadosBean> cargarSanitariosAfectados() throws IOException {

		List<SanitariosAfectadosBean> listaSanitarios = new ArrayList<SanitariosAfectadosBean>();

		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_SANITARIOS_AFECTADOS;
//		descargarFicheros.descargarFicheroSanitariosAfectados();

		String line = "";
		String cvsSplitBy = ";";

		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));
		
		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if(saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				// Creo el bean para cada uno de los hospitales
				SanitariosAfectadosBean sanitariosBean = new SanitariosAfectadosBean();
				sanitariosBean.setFecha(datos[0]);
				sanitariosBean.setProvincia(datos[1]);
				sanitariosBean.setAmbito(datos[2]);
				sanitariosBean.setCategoria(datos[3]);
				sanitariosBean.setTestRealizados(datos[4]);
				sanitariosBean.setTestPositivos(datos[5]);
				sanitariosBean.setAislamiento(datos[6]);
				sanitariosBean.setAlta(datos[7]);
				// Lo añado a la lista
				listaSanitarios.add(sanitariosBean);
				//Cargo los datos a las listas globales
				cargarListaProvincias(datos[1]);
				cargarListaAmbito(datos[2]);
				cargarListaCategoria(datos[3]);
			}
		}
		Comparator<SanitariosAfectadosBean> comparator = Comparator.comparing(SanitariosAfectadosBean::getFecha)
				.reversed().thenComparing(SanitariosAfectadosBean::getProvincia);
		Collections.sort(listaSanitarios, comparator);
		br.close();

		return listaSanitarios;
	}

	public List<SituacionResidenciasBean> cargarSituacionResidencias() throws IOException {

		List<SituacionResidenciasBean> listaSituacionResidencias = new ArrayList<SituacionResidenciasBean>();

		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_SITUACION_RESIDENCIAS;
		
//		descargarFicheros.descargarFicheroSituacionResidencias();

		String line = "";
		String cvsSplitBy = ";";

		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));
		
		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if(saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				// Creo el bean para cada uno de los hospitales
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
				// Lo añado a la lista
				listaSituacionResidencias.add(situacionResBean);
				//Cargo los datos a las listas globales
				cargarListaProvincias(datos[1]);
			}
		}
		Comparator<SituacionResidenciasBean> comparator = Comparator.comparing(SituacionResidenciasBean::getFecha)
				.reversed().thenComparing(SituacionResidenciasBean::getProvincia);
		Collections.sort(listaSituacionResidencias, comparator);
		br.close();

		return listaSituacionResidencias;
	}
	
	public List<CentrosEducacionBean> cargarCentrosEducacion() throws IOException {

		List<CentrosEducacionBean> listaCentrosEducacion = new ArrayList<CentrosEducacionBean>();

		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_CENTROS_EDUCACION;
		
//		descargarFicheros.descargarFicheroCentrosEducacion();

		String line = "";
		String cvsSplitBy = ";";

		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));
		
		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if(saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				// Creo el bean para cada uno de los hospitales
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
				// Lo añado a la lista
				listaCentrosEducacion.add(centrosEduBean);
				//Cargo los datos a las listas globales
				cargarListaProvincias(datos[1]);
			}
		}
		Comparator<CentrosEducacionBean> comparator = Comparator.comparing(CentrosEducacionBean::getFecha)
				.reversed().thenComparing(CentrosEducacionBean::getProvincia);
		Collections.sort(listaCentrosEducacion, comparator);
		br.close();

		return listaCentrosEducacion;
	}

	public List<PruebasRealizadasBean> cargarPruebasRealizadas() throws IOException {

		List<PruebasRealizadasBean> listaPruebasRealizadas = new ArrayList<PruebasRealizadasBean>();

		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_PRUEBAS_REALIZADAS;

//		descargarFicheros.descargarFicheroPruebasRealizadas();

		String line = "";
		String cvsSplitBy = ";";

		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));
		
		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if(saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				// Creo el bean para cada uno de los hospitales
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
				// Lo añado a la lista
				listaPruebasRealizadas.add(pruebasRealizadasBean);
				//Cargo los datos a las listas globales
				cargarListaProvincias(datos[1]);
			}
		}
		Comparator<PruebasRealizadasBean> comparator = Comparator.comparing(PruebasRealizadasBean::getFecha)
				.reversed().thenComparing(PruebasRealizadasBean::getProvincia);
		Collections.sort(listaPruebasRealizadas, comparator);
		br.close();

		return listaPruebasRealizadas;
	}
	
	public List<VacunasRecibidasBean> cargarVacunasRecibidas() throws IOException {

		List<VacunasRecibidasBean> listaVacunasRecibidas = new ArrayList<VacunasRecibidasBean>();

		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_VACUNAS_RECIBIDAS;

//		descargarFicheros.descargarFicheroVacunasRecibidas();

		String line = "";
		String cvsSplitBy = ";";

		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));
		
		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if(saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				// Creo el bean para cada uno de los hospitales
				VacunasRecibidasBean vacunasRecibidasBean = new VacunasRecibidasBean();
				vacunasRecibidasBean.setFecha(datos[0]);
				vacunasRecibidasBean.setProvincia(datos[1]);
				vacunasRecibidasBean.setVacunasRecibidas(datos[2]);
				// Lo añado a la lista
				listaVacunasRecibidas.add(vacunasRecibidasBean);
				//Cargo los datos a las listas globales
				cargarListaProvincias(datos[1]);
			}
		}
		Comparator<VacunasRecibidasBean> comparator = Comparator.comparing(VacunasRecibidasBean::getFecha)
				.reversed().thenComparing(VacunasRecibidasBean::getProvincia);
		Collections.sort(listaVacunasRecibidas, comparator);
		br.close();

		return listaVacunasRecibidas;
	}
	
	public List<PersonasVacunadasBean> cargarPersonasVacunadas() throws IOException {

		List<PersonasVacunadasBean> listaPersonasVacunadas = new ArrayList<PersonasVacunadasBean>();

		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_PERSONAS_VACUNADAS;

//		descargarFicheros.descargarFicheroPersonasVacunadas();

		String line = "";
		String cvsSplitBy = ";";

		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));
		
		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if(saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				// Creo el bean para cada uno de los hospitales
				PersonasVacunadasBean personasVacunadasBean = new PersonasVacunadasBean();
				personasVacunadasBean.setFecha(datos[0]);
				personasVacunadasBean.setProvincia(datos[1]);
				personasVacunadasBean.setPersonasVacunadas(datos[2]);
				// Lo añado a la lista
				listaPersonasVacunadas.add(personasVacunadasBean);
				//Cargo los datos a las listas globales
				cargarListaProvincias(datos[1]);
			}
		}
		Comparator<PersonasVacunadasBean> comparator = Comparator.comparing(PersonasVacunadasBean::getFecha)
				.reversed().thenComparing(PersonasVacunadasBean::getProvincia);
		Collections.sort(listaPersonasVacunadas, comparator);
		br.close();

		return listaPersonasVacunadas;
	}
	
	public List<FallecidosCovidBean> cargarFallecidosCOVID() throws IOException {

		List<FallecidosCovidBean> listaFallecidosCovid = new ArrayList<FallecidosCovidBean>();

		String ficheroCSV = ConstantesCSV.RUTA_FICHERO_FALLECIDOS_COVID;

//		descargarFicheros.descargarFicheroFallecidosCovid();

		String line = "";
		String cvsSplitBy = ";";

		BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));
		
		boolean saltarPrimeraLinea = true;
		while ((line = br.readLine()) != null) {
			if(saltarPrimeraLinea) {
				saltarPrimeraLinea = false;
			} else {
				String[] datos = line.split(cvsSplitBy);
				// Creo el bean para cada uno de los hospitales
				FallecidosCovidBean fallecidosCovidBean = new FallecidosCovidBean();
				fallecidosCovidBean.setFecha(datos[0]);
				fallecidosCovidBean.setProvincia(datos[10]);
				fallecidosCovidBean.setGerencia(datos[2]);
				fallecidosCovidBean.setCentro(datos[4]);
				fallecidosCovidBean.setFallecidos(datos[5]);
				// Lo añado a la lista
				listaFallecidosCovid.add(fallecidosCovidBean);
				//Cargo los datos a las listas globales
				cargarListaProvincias(datos[10]);
				cargarListaCentrosSanitarios(datos[4]);
				cargarListaGerencias(datos[2]);
			
			}
		}
		Comparator<FallecidosCovidBean> comparator = Comparator.comparing(FallecidosCovidBean::getFecha)
				.reversed().thenComparing(FallecidosCovidBean::getProvincia);
		Collections.sort(listaFallecidosCovid, comparator);
		br.close();

		return listaFallecidosCovid;
	}

	public void cargarListaProvincias(String provincia) {
		if (!listaProvincias.contains(provincia)) {
			listaProvincias.add(provincia);
			datosGeneralesView.setListaDeProvincias(listaProvincias);
		}
	}
	
	public void cargarListaHospitales(String hospital) {
		if (!listaHospitales.contains(hospital)) {
			listaHospitales.add(hospital);
			datosGeneralesView.setListaDeHospitales(listaHospitales);
		}
	}
	
	public void cargarListaAmbito(String ambito) {
		if(!listaAmbitoSanitario.contains(ambito)) {
			listaAmbitoSanitario.add(ambito);
			datosGeneralesView.setListaAmbitoSanitario(listaAmbitoSanitario);
		}
	}
	
	public void cargarListaCategoria(String categoria) {
		if (!listaCategoriaSanitario.contains(categoria)) {
			listaCategoriaSanitario.add(categoria);
			datosGeneralesView.setListaCategoriaSanitario(listaCategoriaSanitario);
		}
	}
	
	public void cargarListaCentrosSanitarios(String centro) {
		if(!listaCentrosSanitarios.contains(centro)) {
			listaCentrosSanitarios.add(centro);
			datosGeneralesView.setListaCentrosSanitarios(listaCentrosSanitarios);
		}
	}
	
	public void cargarListaGerencias(String gerencia) {
		if(!listaGerencias.contains(gerencia)) {
			listaGerencias.add(gerencia);
			datosGeneralesView.setListaGerencias(listaGerencias);
		}
	}
	
	public void comprobarEstadoFicheros() {
		File ficheroVacunasRecibidas = new File(ConstantesCSV.RUTA_FICHERO_VACUNAS_RECIBIDAS);
		if(!ficheroVacunasRecibidas.exists()) {
			datosGeneralesView.setDescargandoFicheros(true);
			datosGeneralesView.setFicherosDescargados(false);
		} else {
			datosGeneralesView.setDescargandoFicheros(false);
			datosGeneralesView.setFicherosDescargados(true);
		}
	}
}
