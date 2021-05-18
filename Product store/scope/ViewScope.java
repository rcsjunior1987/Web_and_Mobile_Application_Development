package br.com.unip.tcc.scope;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class ViewScope implements Scope {
	final Logger logger = LoggerFactory.getLogger(ViewScope.class);

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		final Map<String, Object> viewMap = FacesContext.getCurrentInstance()
				.getViewRoot().getViewMap();

		if (viewMap.containsKey(name)) {
			Object bean = viewMap.get(name);

			WebApplicationContext webAppContext = ContextLoader.getCurrentWebApplicationContext();
			AutowireCapableBeanFactory autowireFactory = webAppContext.getAutowireCapableBeanFactory();

			if (webAppContext.containsBean(name)) {
				bean = autowireFactory.configureBean(bean, name);
			}
			return bean;
		} else {
			final Object object = objectFactory.getObject();
			viewMap.put(name, object);

			return object;
		}
	}

	@Override
	public String getConversationId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerDestructionCallback(String arg0, Runnable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object remove(String name) {
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap()
				.remove(name);
	}

	@Override
	public Object resolveContextualObject(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}	


}
