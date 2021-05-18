package br.com.unip.tcc.exceptions;

public class FieldsUnfilledException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public FieldsUnfilledException() {
		super("You have fields unfilled");
	}
	
}
