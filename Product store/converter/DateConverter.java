package br.com.unip.tcc.converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Calendar.class, value = "dateConverter")
public class DateConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
		spf.setLenient(false);
		try {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(spf.parse(value));
				return calendar;
		} catch (ParseException e) {
			throw new ConverterException();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Calendar calendar = (Calendar) value;
		Date date = calendar.getTime();
		SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");  
		return spf.format(date);  
	}
}
