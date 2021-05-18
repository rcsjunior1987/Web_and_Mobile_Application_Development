package br.com.unip.tcc.system;

public enum ScreenTitles {

	LISTAR_USUARIO("Listar usuário"),
	INCLUIR_USUARIO("Inserir usuário");
	
	private String title;

	private ScreenTitles(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
