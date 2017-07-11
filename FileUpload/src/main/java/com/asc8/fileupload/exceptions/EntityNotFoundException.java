package com.asc8.fileupload.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException() {
        super("Entity Not Found");
    }

    public EntityNotFoundException(String msg) {
        super(msg);
    }

}
