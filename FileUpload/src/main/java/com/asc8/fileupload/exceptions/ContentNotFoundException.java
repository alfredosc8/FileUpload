package com.asc8.fileupload.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ContentNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public ContentNotFoundException() {
        super("Content Not Found");
    }

    public ContentNotFoundException(String msg) {
        super(msg);
    }

}
