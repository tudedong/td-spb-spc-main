package com.td.api.provider.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.td.api.provider.bean.Json;
import com.td.api.provider.server.base.BaseServer;
import com.td.api.provider.service.DemoService;

/**
 * @author tudedong
 * @description api服务提供者（demo实例）
 * @date 2019-12-12 19:10:33
 */
@RestController
public class DemoServer extends BaseServer {
	@Autowired
	private DemoService demoService;

	/**
	 * 获取公告信息
	 *
	 * @param noticeId
	 * @return
	 */
	@RequestMapping(value = "/getNotice/{noticeId}", method = RequestMethod.GET)
	@ResponseBody
	public Json getNotice(@PathVariable Long noticeId) {
		return demoService.getNotice(noticeId);
	}

	/**
	 * 获取公告列表
	 *
	 * @param noticeId
	 * @return
	 */
	@RequestMapping(value = "/getNoticeList", method = RequestMethod.POST)
	@ResponseBody
	public Json getNoticeList() {
		return demoService.getNoticeList();
	}

}
