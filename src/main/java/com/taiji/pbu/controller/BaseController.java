package com.taiji.pbu.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.util.NestedServletException;

@Component
public class BaseController {

	private final Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 统一的异常处理
	 *
	 * @param ex
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws NestedServletException
	 * @throws JSONException
	 */
	@ExceptionHandler(value = { Exception.class, Exception.class })
	public void actionExceptionHandler(Throwable ex, HttpServletRequest request, HttpServletResponse response)
			throws IOException, NestedServletException, JSONException {

		redirectErrorPage("服务异常，请稍后再试。", request, response);
		logger.error(ex.getMessage(),ex);
		return;
	}

	private void redirectErrorPage(String message, HttpServletRequest request,
								   HttpServletResponse response) throws IOException {
		request.setAttribute("message", message);
	
		RequestDispatcher view = null;

		if(message.equals("xxxxx")){
			response.sendRedirect("xxxx");
		}
		else{
			view = request.getRequestDispatcher("/jsp/login.jsp");
		}


		try {
			if (view != null) {
				view.forward(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * 操作成功返回的json数据
	 *
	 * @param jsonObject
	 * @param message
	 * @return
	 * @throws JSONException
	 */
	protected JSONObject setSuccessResult(JSONObject jsonObject, String message) throws JSONException {
		jsonObject.put(RESULT, SUCCESS);
		jsonObject.put(MESSAGE, message);
		return jsonObject;
	}

	protected JSONObject setSuccessResult(JSONObject jsonObject,
										  String message, String paramMap) throws JSONException {
		jsonObject.put(RESULT, SUCCESS);
		jsonObject.put(MESSAGE, message);
		jsonObject.put("paramMap", paramMap);
		return jsonObject;
	}

	protected void responseWrite(HttpServletResponse response,
								 JSONObject jsonObject) throws IOException, JSONException {
		final int jsonToString = 3;
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonObject.toString(jsonToString));
	}
	
	protected void responseWrite(HttpServletResponse response, String message)
			throws IOException {

		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(message);
	}

	protected void responseWrite(HttpServletResponse response, String message,
								 int cacheTimesLong) throws IOException {
		java.util.Date date = new java.util.Date();
		response.setDateHeader("Last-Modified", date.getTime());
		response.setDateHeader("Expires", date.getTime() + cacheTimesLong);

		response.setHeader("Cache-Control", "public");
		response.setHeader("Pragma", "Pragma");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(message);
	}

	protected void responseWrite(HttpServletResponse response,
								 JSONArray jsonObject) throws IOException, JSONException {
		final int jsonToString = 3;
		response.getWriter().write(jsonObject.toString(jsonToString));
	}

	/**
	 *
	 * 操作失败返回的json数据
	 *
	 * @param jsonObject
	 * @param message
	 * @return
	 * @throws JSONException
	 */
	protected JSONObject setFailResult(JSONObject jsonObject, String message) throws JSONException {
		jsonObject.put(RESULT, "fail");
		jsonObject.put(MESSAGE, message);
		return jsonObject;
	}

	protected JSONObject setFailResult(JSONObject jsonObject, String message,
									   String failType) throws JSONException {
		jsonObject.put(VALIDATE_TYPE, failType);
		jsonObject.put(RESULT, "fail");
		jsonObject.put(MESSAGE, message);
		return jsonObject;
	}


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	protected static final Integer DEFAULTP_AGESIZE = 10;
	protected static final String RESULT = "result";
	protected static final String VALIDATE_TYPE = "resultType";
	protected static final String SUCCESS = "success";
	protected static final String MESSAGE = "message";

}
