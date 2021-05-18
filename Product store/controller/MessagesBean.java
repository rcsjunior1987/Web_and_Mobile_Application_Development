package br.com.unip.tcc.controller;

import javax.faces.bean.ManagedBean;
import br.com.unip.tcc.system.Messages;

@ManagedBean(name = "messagesBean")
public class MessagesBean {
	public String getM7() {
		return Messages.M7.getMessage();
	}
	public String getM10() {
		return Messages.M10.getMessage();
	}
	public String getM18() {
		return Messages.M18.getMessage();
	}
	public String getM19() {
		return Messages.M19.getMessage();
	}
	public String getM20() {
		return Messages.M20.getMessage();
	}
	public String getM21() {
		return Messages.M21.getMessage();
	}
	public String getM22() {
		return Messages.M22.getMessage();
	}
	public String getM23() {
		return Messages.M23.getMessage();
	}
}