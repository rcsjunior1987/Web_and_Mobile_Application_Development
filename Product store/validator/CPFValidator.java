package br.com.unip.tcc.validator;

public class CPFValidator {
	public static boolean isValid(String cpf) {  
        //Se o CPF não tiver 11 dígitos é inválido.  
        if (cpf.length() != 11)  
            return false;  
  
        //Guardará os dígitos gerados.  
        int[] digitos = new int[2];  
  
        //Gera os dois últimos dígitos.  
        for (int multiplicador=10; multiplicador <= 11; multiplicador++) {  
            int soma = 0;  
            //Faz as multiplicações e já soma os resultados.  
            for (int i=0; i < (multiplicador-1); i++) {  
                soma += Integer.parseInt(cpf.substring(i, i+1)) * (multiplicador - i);  
            }  
            //verifica o resto.  
            int resto = (soma % 11);  
            //Carrega a posição do array.  
            if (resto < 2) {  
                digitos[multiplicador-10] = 0;  
            } else {  
               digitos[multiplicador-10] = 11 - resto;  
            }  
        }  
        //Retorna true se os dois últimos dígitos da string forem iguais os gerados.  
        return (digitos[0] == Integer.parseInt(cpf.substring(9, 10)) &&  
                digitos[1] == Integer.parseInt(cpf.substring(10, 11)));  
    }
}
