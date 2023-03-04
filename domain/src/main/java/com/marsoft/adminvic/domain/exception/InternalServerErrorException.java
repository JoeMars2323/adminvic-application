package com.marsoft.adminvic.domain.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends AdminVicException {
	private static final long serialVersionUID = -6870732210014274010L;

	public InternalServerErrorException(final String message) {
		super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}

	public InternalServerErrorException(final String message, final AdminVicError data) {
		super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Arrays.asList(data));
	}
}
