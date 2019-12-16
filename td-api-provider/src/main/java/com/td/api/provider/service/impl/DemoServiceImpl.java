package com.td.api.provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.td.api.provider.bean.Json;
import com.td.api.provider.enums.ResultEnum;
import com.td.api.provider.service.DemoService;
import com.td.api.provider.util.ResultUtil;
import com.td.system.domain.SysNotice;
import com.td.system.mapper.SysNoticeMapper;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private ResultUtil resultUtil;

	@Autowired
	private SysNoticeMapper sysNoticeMapper;

	@Override
	public Json getNotice(Long noticeId) {
		if (StringUtils.isEmpty(noticeId)) {
			return resultUtil.returnOther(ResultEnum.PARAMERROR.getCode(), ResultEnum.PARAMERROR.getMsg());
		}
		SysNotice notice = sysNoticeMapper.selectNoticeById(noticeId);
		if (notice == null) {
			return resultUtil.returnOther(ResultEnum.PARAMNULL.getCode(), ResultEnum.PARAMNULL.getMsg());
		}
		return resultUtil.returnSuccess(notice);
	}

	@Override
	public Json getNoticeList() {
		return resultUtil.returnSuccess(sysNoticeMapper.selectNoticeList(null));
	}

}
