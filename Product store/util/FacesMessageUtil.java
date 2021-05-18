package br.com.unip.tcc.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.com.unip.tcc.system.Messages;

public class FacesMessageUtil {
  
    public static void addMenssagem(Severity severity, FacesMessage facesMessage) {
    	FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
	
	public static void showMessage(FacesMessage facesMessage) {
		addMenssagem(facesMessage.getSeverity(), facesMessage);
	}
	
	public static void showMessageInfo(String message) {
		FacesMessageUtil.showMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação:", message));
	}

	public static void showMessageWarn(String message) {
		FacesMessageUtil.showMessage(new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta:", message));
	}

	public static void showMessageError(String message) {
		FacesMessageUtil.showMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", message));
	}

	public static void showMessageInfo(Messages message) {
		showMessageInfo(message.getMessage());
	}

	public static void showMessageWarn(Messages message) {
		showMessageWarn(message.getMessage());
	}

	public static void showMessageError(Messages message) {
		showMessageError(message.getMessage());
	}
}
