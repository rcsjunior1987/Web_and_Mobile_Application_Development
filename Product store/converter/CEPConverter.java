package br.com.unip.tcc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "cepConverter")
public class CEPConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		StringBuffer cep = new StringBuffer();
		
		if(arg2 != null && !arg2.equals("")) {
			cep.append(arg2.toString());
			cep.insert(5,".");
			cep.insert(2,".");
		}
		
        return cep.toString();
	}
}
