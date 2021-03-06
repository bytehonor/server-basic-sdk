package com.bytehonor.sdk.basic.server.validate;

import java.util.Objects;

import com.bytehonor.sdk.basic.lang.string.StringCreator;
import com.bytehonor.sdk.basic.server.exception.ParameterExcption;

public class LongValidate {

	public static void range(Long src, long min, long max, String message) {
		Objects.requireNonNull(src, message);
		if (src.longValue() < min) {
			throw new ParameterExcption(StringCreator.create().append(message).append(" < ").append(min).toString());
		}
		if (src.longValue() > max) {
			throw new ParameterExcption(StringCreator.create().append(message).append(" > ").append(max).toString());
		}
	}
}
