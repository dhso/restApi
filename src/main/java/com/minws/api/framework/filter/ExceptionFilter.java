package com.minws.api.framework.filter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minws.api.framework.Constant.HeaderType;
import com.minws.api.framework.Constant.RespondType;
import com.minws.api.framework.exception.RestResult;

@Provider
public class ExceptionFilter implements ExceptionMapper<Exception> {
	private static Logger logger = LoggerFactory.getLogger(ExceptionFilter.class);

	@Override
	public Response toResponse(Exception e) {
		RestResult result = new RestResult(RespondType.ERROR_400, e.getMessage());
		logger.error("--> " + e.getMessage());
		return Response.ok(result, MediaType.APPLICATION_JSON).header(HeaderType.CORS_ACAO, "*").build();
	}
}