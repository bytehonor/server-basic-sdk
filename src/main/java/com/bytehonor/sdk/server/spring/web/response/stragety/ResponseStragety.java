package com.bytehonor.sdk.server.spring.web.response.stragety;

import org.springframework.http.server.ServerHttpRequest;

import com.bytehonor.sdk.protocol.common.result.JsonResponse;

public interface ResponseStragety {

	JsonResponse<?> process(ServerHttpRequest request);
}
