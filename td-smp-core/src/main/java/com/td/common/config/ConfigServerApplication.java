package com.td.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RefreshScope
@RestController
@Repository
@Data
public class ConfigServerApplication {
	/**
	 * 名称
	 */
	@Value("${td.name}")
	private String name;
	/**
	 * 版本
	 *
	 */
	@Value("${td.version}")
	private String version;
	/**
	 * 版权年份
	 */
	@Value("${td.copyrightYear}")
	private String copyrightYear;

	/**
	 * 实例演示开关
	 */
	@Value("${td.demoEnabled}")
	private String demoEnabled;

	/**
	 * 获取头像上传路径
	 */
	@Value("${td.avatarPath}")
	private String avatarPath;

	/**
	 * 获取下载路径
	 */
	@Value("${td.uploadPath}")
	private String uploadPath;

	/**
	 * 获取ip地址开关
	 */
	@Value("${td.addressEnabled}")
	private String addressEnabled;

	/**
	 * 文件路径
	 */
	@Value("${td.profile}")
	private String profile;

	/**
	 * 获取下载路径
	 */
	@Value("${td.downloadPath}")
	private String downloadPath;

	/**
	 * 限制操作ip
	 */
	@Value("${td.ips}")
	private String ips;

	/**
	 * 刷新配置中心地址
	 */
	@Value("${td.config.refresh}")
	private String refresh;
	/**
	 * 高德地图key
	 */
	@Value("${td.gd_map_key}")
	private String gd_map_key;

	/**
	 * api服务url地址
	 */
	@Value("${td.api_url}")
	private String api_url;

}
