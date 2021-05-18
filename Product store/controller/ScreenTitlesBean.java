package br.com.unip.tcc.controller;

import javax.faces.bean.ManagedBean;

import br.com.unip.tcc.system.ScreenTitles;

@ManagedBean(name = "screenTitlesBean")
public class ScreenTitlesBean {
	
	public String getScreenTitles() {
		String screenTitles = "LISTAR_USUARIO";
		String title = "";
		switch (screenTitles) {
		case "LISTAR_USUARIO":
			title = ScreenTitles.INCLUIR_USUARIO.getTitle();
			break;
		default:
			break;
		}
		
		return title;
	}
	
}
