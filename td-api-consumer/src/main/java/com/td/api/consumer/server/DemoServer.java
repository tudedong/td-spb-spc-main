package com.td.api.consumer.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.td.api.consumer.aop.LogAnnotation;
import com.td.api.consumer.aop.LogAnnotation.OPERTYPE;
import com.td.api.consumer.service.DemoService;

/**
 * @author tudedong
 * @description api服务消费者（demo实例）
 * @date 2019-12-12 19:10:33
 */
@RestController
public class DemoServer {
	@Autowired
	private DemoService demoService;

	@RequestMapping(value = "/getNotice/{noticeId}", method = RequestMethod.GET)
	@ResponseBody
	@LogAnnotation(oper = OPERTYPE.getNotice)
	public String getNotice(@PathVariable(value="noticeId") Long noticeId) {
		return demoService.getNotice(noticeId);
	}

	@RequestMapping(value = "/getNoticeList", method = RequestMethod.POST)
	@ResponseBody
	@LogAnnotation(oper = OPERTYPE.getNotice)
	public String getNoticeList() {
		return demoService.getNoticeList();
	}

}
