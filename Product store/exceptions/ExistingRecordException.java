package br.com.unip.tcc.exceptions;

public class ExistingRecordException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public ExistingRecordException() {
		super("Record already exists");
	}
}
