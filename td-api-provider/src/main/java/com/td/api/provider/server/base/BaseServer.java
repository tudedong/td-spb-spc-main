package com.td.api.provider.server.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tudedong
 * @description 基础服务
 * @date 2019-12-12 19:10:33
 */
@RestController
public class BaseServer {

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpSession session;
}
