package com.bytehonor.sdk.basic.server.web.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.basic.server.exception.InternalRestfulException;
import com.bytehonor.sdk.basic.server.exception.ServerBasicException;
import com.bytehonor.sdk.basic.server.web.error.stragety.DefaultExceptionStragety;
import com.bytehonor.sdk.basic.server.web.error.stragety.InternalRestfulExceptionStragety;
import com.bytehonor.sdk.basic.server.web.error.stragety.NullPointerExceptionStragety;
import com.bytehonor.sdk.basic.server.web.error.stragety.ServerDefinedExceptionStragety;

public class ExceptionStragetyFactory {

	private static final Logger LOG = LoggerFactory.getLogger(ExceptionStragetyFactory.class);

	public static final ExceptionStragety build(Exception e) {

		if (e instanceof ServerBasicException) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("ServerDefinedException process");
			}
			return new ServerDefinedExceptionStragety((ServerBasicException) e);
		}

		if (e instanceof InternalRestfulException) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("InternalRestfulException process");
			}
			return new InternalRestfulExceptionStragety((InternalRestfulException) e);
		}

		if (e instanceof NullPointerException) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("NullPointerException process");
			}
			return new NullPointerExceptionStragety((NullPointerException) e);
		}

		return new DefaultExceptionStragety(e);
	}

}
