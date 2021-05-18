package br.com.unip.tcc.system;

public enum Messages {
	M1("Nenhum registro encontrado"),
	M2("Campos obrigatórios não preenchidos"),
	M3("Registro já cadastrado"),
	M4("Confirmação de senha inválida"),
	M5("Registro incluido com sucesso"),
	M6("Registro alterado com sucesso"),
	M7("Deseja realmente cancelar esta ação?"), 
	M8("Registro ativado com sucesso"), 
	M9("Registro inativado com sucesso"),
	M10("Deseja realmente remover este registro?"),
	M11("Não foi possivel remover, há vínculos com este registro"),
	M12("Não foi possivel remover, registro já removido"),
	M13("Registro removido com sucesso"),
	M14("Login ou senha inválido"),
	M15("Funcionalidade já existente"),
	M16("Valores inválidos"),
	M17("CPF inválido"),
	M18("Deseja realmente confirmar esta cotação?"),
	M19("Deseja realmente cancelar esta cotação?"),
	M20("Deseja realmente confirmar esta compra?"),
	M21("Cotação sem item não pode ser confirmada"),
	M22("Compra sem item não pode ser confirmada"),
	M23("Deseja realmente confirmar esta venda?");
	
	private String message;

	private Messages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
