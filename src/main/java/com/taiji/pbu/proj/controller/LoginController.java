package com.taiji.pbu.proj.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.taiji.pbu.controller.BaseController;
import com.taiji.pbu.controller.Constants;
import com.taiji.pbu.controller.CookieUtils;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource
	private CookieUtils cookieUtils;

	@Value("${where}")
	private String where;


    /**
	 * 显示登录页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("login/login");
		
		return mav;
	}
	
	/**
	 * 显示欢迎页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/welcome")
	public ModelAndView welcome(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("welcome");
		mav.addObject("where", where);
		
		return mav;
	}

	
	/**
	 * 执行登录逻辑
	 * @param request
	 * @param response
	 * @return
	 */
//	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
//	@ResponseBody
//	public BaseDto doLogin(HttpServletRequest request, HttpServletResponse response, LoginParam loginParam) {
//		BaseDto result = new BaseDto();
//		SessionContext userContext = loginApi.doLogin(loginParam);
//		if (BaseDto.RET_SUCCESS.equals(userContext.getRetCode())) {
//			cookieUtils.addCookie(request, response, Constants.SESSION_KEY, userContext.getSessionKey(), 0);
//
//			List<Object> list = new ArrayList<Object>();
//			list.add(userContext.getAccount());
//			list.add(userContext.getSessionKey());
//
//			result.setRetCode(BaseDto.RET_SUCCESS);
//			result.setData(list);
//		}
//		else {
//			logger.error("登录失败！", result.getRetMsg());
//			result.setRetCode(BaseDto.RET_FAILURE);
//			result.setRetMsg(userContext.getRetMsg());
//		}
//		return result;
//	}
}
