package com.td.api.consumer.service.impl;

import org.springframework.stereotype.Service;

import com.td.api.consumer.service.DemoService;
import com.td.api.consumer.service.base.BaseService;

@Service
public class DemoServiceImpl extends BaseService implements DemoService {

	@Override
	public String getNotice(Long noticeId) {
		return returnHix();
	}

	@Override
	public String getNoticeList() {
		return returnHix();
	}

}
