package com.asc8.fileupload.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class MissingMandatoryException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MissingMandatoryException() {
        super("Mandatory Field/s are missing");
    }

    public MissingMandatoryException(String msg) {
        super(msg);
    }

}
