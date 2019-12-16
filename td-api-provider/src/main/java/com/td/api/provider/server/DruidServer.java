package com.td.api.provider.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tudedong
 * @description druid 监控
 * @date 2019-12-12 19:10:33
 */
@Controller
@RequestMapping("/monitor/data")
public class DruidServer {
	@GetMapping()
	public String index() {
		return "redirect:/monitor/druid/index";
	}
}
