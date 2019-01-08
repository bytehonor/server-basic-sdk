package com.bytehonor.sdk.server.spring.web.restful.factory;

import org.springframework.core.MethodParameter;
import org.springframework.http.server.ServerHttpResponse;

import com.bytehonor.protocol.core.server.result.JsonResponse;
import com.bytehonor.sdk.server.spring.config.SpringBootStandardProperties;
import com.bytehonor.sdk.server.spring.web.error.exception.entity.ExceptionEntity;
import com.bytehonor.sdk.server.spring.web.restful.stragety.ResponseStragety;
import com.bytehonor.sdk.server.spring.web.restful.stragety.ReturnDirectResponseStragety;
import com.bytehonor.sdk.server.spring.web.restful.stragety.ReturnErrorResponseStragety;
import com.bytehonor.sdk.server.spring.web.restful.stragety.ReturnNormalResponseStragety;
import com.bytehonor.sdk.server.spring.web.restful.stragety.ReturnNullResponseStragety;

public class ResponseStragetyFactory {
	
	@SuppressWarnings("rawtypes")
	public static ResponseStragety build(Object body, ServerHttpResponse response, MethodParameter returnType, SpringBootStandardProperties standardSpringBootProperties) {
		if (body == null) {
			return new ReturnNullResponseStragety(response, standardSpringBootProperties);
		}

		if (body instanceof ExceptionEntity) {
			return new ReturnErrorResponseStragety((ExceptionEntity) body, response, standardSpringBootProperties);
		}
		
		if (body instanceof JsonResponse) {
			return new ReturnDirectResponseStragety((JsonResponse) body, response, standardSpringBootProperties);
		}

		
		return new ReturnNormalResponseStragety(body, response);
	}

}
