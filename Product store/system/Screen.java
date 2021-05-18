package br.com.unip.tcc.system;

public enum Screen {
	LISTAR_USUARIO("incluir_usuario"),
	INCLUIR_USUARIO("listar_usuario"),
	LISTAR_PRODUTO("incluir_produto"),
	INCLUIR_PRODUTO("listar_produto"),
	LISTAR_CLIENTE("incluir_cliente"),
	INCLUIR_CLIENTE("listar_cliente"),
	LISTAR_COTACAO("incluir_cotacao"),
	INCLUIR_COTACAO("listar_cotacao"),
	LISTAR_COTACAOITENS("incluir_cotacaoitens"),
	LISTAR_FORNECEDOR("incluir_fornecedor"),
	INCLUIR_FORNECEDOR("listar_fornecedor"),
	LISTAR_COMPRA("incluir_compra"),
	INCLUIR_COMPRA("listar_compra"),
	LISTAR_COMPRAITENS("incluir_compraitens"),
	LISTAR_VENDA("incluir_venda"),
	INCLUIR_VENDA("listar_venda"),
	LISTAR_VENDAITENS("incluir_vendaitens");
	
	private String url;

	private Screen(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}
	
	public static Screen getScreenOf(String url){
		for (Screen screen : Screen.values()) {
			if(screen.url.endsWith(url))
				return screen;
		}
		return null;
	}
}
