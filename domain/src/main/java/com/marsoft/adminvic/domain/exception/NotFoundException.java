package com.marsoft.adminvic.domain.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AdminVicException {
	private static final long serialVersionUID = -6870732210014274010L;

	public NotFoundException(final String message) {
		super(HttpStatus.NOT_FOUND.value(), message);
	}

	public NotFoundException(final String message, final AdminVicError data) {
		super(HttpStatus.NOT_FOUND.value(), message, Arrays.asList(data));
	}
}
