package br.com.unip.tcc.util;

import java.io.IOException;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.unip.tcc.system.Screen;

public class FacesUtil {

	public static String facesRedirectUrl(String destinationURL) {
		return destinationURL == null ? "" : destinationURL;
	}

	public static String facesRedirectUrl(Screen screen) {
		return facesRedirectUrl(screen.getUrl()) + "?faces-redirect=true";
	}
	
	public static Map<String, Object> getSessionMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}
	
	public static void createSession(String key, Integer value) {
		getSessionMap().put(key, value);
	}
	
	public static boolean isContainsKey(String key) {
		return getSessionMap().containsKey(key);
	}
	
	public static String getValueSessionMap(String key) {
		return getSessionMap().get(key).toString();
	}
	
	public static void removeSession(String key) {
		getSessionMap().remove(key);
	}
	
	public static String getRequestUrl() {
		Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return request == null ? "" : ((HttpServletRequest) request).getRequestURI();
	}
	
	public static void redirect(String screen) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
