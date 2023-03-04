package com.marsoft.adminvic.domain.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.marsoft.adminvic.domain.response.AdminVicResponse;
import com.marsoft.adminvic.domain.utils.ExceptionConstants;

@ControllerAdvice
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AdminVicRestExceptionHandler {

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public AdminVicResponse unhandledErrors(HttpServletRequest req, Exception ex) {
		return new AdminVicResponse(ExceptionConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
				ex.getMessage());
	}

	@ExceptionHandler({ AdminVicException.class })
	@ResponseBody
	public AdminVicResponse handleLmException(final HttpServletRequest request, final HttpServletResponse response,
			final AdminVicException ex) {
		response.setStatus(ex.getCode());
		return new AdminVicResponse(ExceptionConstants.ERROR, String.valueOf(ex.getCode()), ex.getMessage(),
				ex.getErrorList());
	}
}
