package com.roberto.SCCyL.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ConstantesTexto {

	private final String TEXTO_FOOTER = "Developed by Roberto Esteban. All rights reserved ©";

	public String getTEXTO_FOOTER() {
		return TEXTO_FOOTER;
	}

}
