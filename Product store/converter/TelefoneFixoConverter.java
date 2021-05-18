package br.com.unip.tcc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "telefoneFixoConverter")
public class TelefoneFixoConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		StringBuffer telefone = new StringBuffer();

		if(arg2 != null && !arg2.equals("")) {
			telefone.append(arg2.toString());

			telefone.insert(7,"-");
			telefone.insert(3," ");
			telefone.insert(3,")");
			telefone.insert(0,"(");
		}
        return telefone.toString();
	}
}
