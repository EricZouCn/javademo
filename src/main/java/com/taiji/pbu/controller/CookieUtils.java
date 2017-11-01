package com.taiji.pbu.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public final class CookieUtils {

	@Value("${cookie_domain}")
	private String domain;

	/**
	 * 设置cookie（接口方法）
	 * 
	 * @param response
	 * @param name
	 *            cookie名字
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            cookie生命周期 以秒为单位
	 */
	public void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
			int maxAge) {
		Cookie cookie = getCookieByName(request, name);
		if (cookie != null) {
			cookie.setValue(value);
		}else{
			cookie = new Cookie(name, value);
		}

		if (StringUtils.isNotBlank(domain)) {
			cookie.setDomain(domain);
		}
		cookie.setPath("/");
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}
	
	public void removeCookie(HttpServletResponse response, String cookieName){
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
        response.addCookie(cookie);
	}

	/**
	 * 根据名字获取cookie（接口方法）
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public  Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面（非接口方法）
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	
	
	/**
	 * 将cookie封装到Map里面（非接口方法）
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, String> ReadCookieValueMap(HttpServletRequest request) {
		Map<String, String> cookieMap = new HashMap<String, String>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie.getValue());
			}
		}
		return cookieMap;
	}
}
