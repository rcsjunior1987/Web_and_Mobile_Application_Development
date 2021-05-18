package br.com.unip.tcc.converter;
import javax.faces.component.UIComponent;  
import javax.faces.context.FacesContext;  
import javax.faces.convert.Converter;  
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "rgConverter")
public class RGConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		StringBuffer rg = new StringBuffer();
		
		if(arg2 != null && !arg2.equals("")) {
			rg.append(arg2.toString());
		
			rg.insert(8,"-");  
			rg.insert(2,".");  
			rg.insert(6,".");
		}
        return rg.toString();
	}
}
