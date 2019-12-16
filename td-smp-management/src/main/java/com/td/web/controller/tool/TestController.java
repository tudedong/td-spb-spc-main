package com.td.web.controller.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.esotericsoftware.minlog.Log;
import com.td.common.config.ConfigServerApplication;
import com.td.common.core.controller.BaseController;
import com.td.common.core.domain.AjaxResult;
import com.td.common.utils.http.HttpUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author tudedong
 * @description swagger 用户测试方法
 * @date 2019-12-12 19:10:33
 */
@Api("公告信息管理")
@RestController
@RequestMapping("/test/user")
public class TestController extends BaseController {
	@Autowired
	private ConfigServerApplication configServerApplication;

	@ApiOperation("获取公告信息")
	@ApiImplicitParam(name = "noticeId", value = "公告ID", required = true, dataType = "long", paramType = "path")
	@GetMapping("/{noticeId}")
	public AjaxResult getNotice(@PathVariable Integer noticeId) {
		HttpUtil httpUtil = HttpUtil.getInstance("utf-8", 1000, 1000);
		String resullt = "";
		try {
			String url_p = this.getUrl(configServerApplication.getApi_url(), "/getNotice/", noticeId);
			//Log.info("[信息]请求地址：" + url_p);
			resullt = httpUtil.sendHttpGet(url_p, null, null, MimeTypeUtils.APPLICATION_JSON);
		} catch (Exception e) {
			return error("请求发生异常");
		}
		return AjaxResult.success(resullt);
	}

	@ApiOperation("获取公告列表")
	@PostMapping("/getNoticeList")
	public AjaxResult getNoticeList() {
		HttpUtil httpUtil = HttpUtil.getInstance("utf-8", 1000, 1000);
		String resullt = "";
		try {
			String url_p = this.getUrl(configServerApplication.getApi_url(), "/getNoticeList");
			//Log.info("[信息]请求地址：" + url_p);
			resullt = httpUtil.sendHttpPost(url_p, null, null, null, MimeTypeUtils.APPLICATION_JSON);
		} catch (Exception e) {
			return error("请求发生异常");
		}
		return AjaxResult.success(resullt);
	}

	private String getUrl(Object... onObjects) {
		StringBuffer buffer = new StringBuffer();
		for (Object param : onObjects) {
			buffer.append(param);
		}
		return buffer.toString();
	}

}
