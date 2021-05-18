package br.com.unip.tcc.exceptions;

public class CannotDeleteException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public CannotDeleteException() {
		super("Cannot delete!");
	}
}
