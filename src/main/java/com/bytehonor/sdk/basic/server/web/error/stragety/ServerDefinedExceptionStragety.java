package com.bytehonor.sdk.basic.server.web.error.stragety;

import org.springframework.http.HttpStatus;

import com.bytehonor.sdk.basic.server.exception.ServerBasicException;
import com.bytehonor.sdk.basic.server.web.error.ExceptionHolder;
import com.bytehonor.sdk.basic.server.web.error.ExceptionStragety;

public class ServerDefinedExceptionStragety implements ExceptionStragety {
	
	private ServerBasicException exception;
	
	public ServerDefinedExceptionStragety(ServerBasicException exception) {
		this.exception = exception;
	}

	@Override
	public ExceptionHolder hold() {
		ExceptionHolder error = new ExceptionHolder();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setCode(exception.getCode());
    	error.setException(exception);
        return error;
	}

}
