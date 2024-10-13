package com.findfound.demo.util;



import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpSession;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.findfound.demo.constant.StatusCode;
import com.findfound.demo.response.ErrorObject;
import com.findfound.demo.response.Response;


public class CommonUtils {
	static ObjectMapper mapper = new ObjectMapper();
	private static String addStr = "globalPORTALAccessKEY";
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CommonUtils.class);
	private static HttpSession session = null;
	public static String getJson(Object obj) {
		try {
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("getJsonResponse:Error in json processing: ", e);
		}
		return "";
	}
	public static Object getObject(String str) throws IOException {
		try {
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			return mapper.readValue(str, Object.class);
		} catch (JsonProcessingException e) {
			logger.error("getJsonResponse:Error in json processing: ", e);
		}
		return "";
	}

	public static Response getResponseObject(String message) {
		Response response = new Response();
		response.setStatus(StatusCode.SUCCESS.name());
		response.setMessage(message);
		return response;
	}

	public static ErrorObject getErrorResponse(String title, String detail) {
		ErrorObject errorObject = new ErrorObject();
		errorObject.setTitle(title);
		errorObject.setDetail(detail);
		return errorObject;
	}
	public static HttpSession geSsession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		if (session == null)
			session = attr.getRequest().getSession(true );
		System.out.println(session);
		return session;
	}

	public static String getUserId(String userName) {
		HttpSession session = CommonUtils.geSsession();
		session.setAttribute("name", userName);
		return (String) session.getAttribute("name");
	}
	public static JSONObject getJson(CloseableHttpResponse httpResponse) throws IOException, JsonParseException {
		try {
			String result = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8.name());
			JSONObject object = new JSONObject(result);

			return object;
		} catch (Exception e) {
			logger.error("Exception in getJson : " + " ", e);
		}
		return null;
	}
}
