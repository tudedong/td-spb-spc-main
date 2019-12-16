package com.td.web.controller.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.td.common.config.ConfigServerApplication;

/**
 * @author tudedong
 * @description 地图演示
 * @date 2019-12-12 19:10:33
 */
@Controller
@RequestMapping("/demo/map")
public class DemoMapController {
	@Autowired
	private ConfigServerApplication configServerApplication;

	private String prefix = "demo/map";

	/**
	 * 添加信息窗口
	 */
	@GetMapping("/addmap")
	public String addmap() {
		return prefix + "/addmap";
	}

	/**
	 * 添加信息选择地点窗口
	 */
	@GetMapping("/selectmap")
	public String selectmap(ModelMap mmap) {
		mmap.put("gd_map_key", configServerApplication.getGd_map_key());
		return prefix + "/selectmap";
	}
}
