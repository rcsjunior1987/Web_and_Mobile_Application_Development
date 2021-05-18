package br.com.unip.tcc.system;

public enum Messages {
	M1("Nenhum registro encontrado"),
	M2("Campos obrigat�rios n�o preenchidos"),
	M3("Registro j� cadastrado"),
	M4("Confirma��o de senha inv�lida"),
	M5("Registro incluido com sucesso"),
	M6("Registro alterado com sucesso"),
	M7("Deseja realmente cancelar esta a��o?"), 
	M8("Registro ativado com sucesso"), 
	M9("Registro inativado com sucesso"),
	M10("Deseja realmente remover este registro?"),
	M11("N�o foi possivel remover, h� v�nculos com este registro"),
	M12("N�o foi possivel remover, registro j� removido"),
	M13("Registro removido com sucesso"),
	M14("Login ou senha inv�lido"),
	M15("Funcionalidade j� existente"),
	M16("Valores inv�lidos"),
	M17("CPF inv�lido"),
	M18("Deseja realmente confirmar esta cota��o?"),
	M19("Deseja realmente cancelar esta cota��o?"),
	M20("Deseja realmente confirmar esta compra?"),
	M21("Cota��o sem item n�o pode ser confirmada"),
	M22("Compra sem item n�o pode ser confirmada"),
	M23("Deseja realmente confirmar esta venda?");
	
	private String message;

	private Messages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
