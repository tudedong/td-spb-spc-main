package com.td.api.provider.bean;

import lombok.Data;

/**
 * @author tudedong
 * @description 响应信息bean
 * @date 2019-12-12 19:10:33
 */
@Data
public class Json {
	/**
	 * 响应码
	 */
	private String code;
	/**
	 * 响应说明
	 */
	private String msg;
	/**
	 * 响应数据
	 */
	private Object obj;

	public Object getObj() {
		if (obj == null) {
			return "";
		}
		return obj;
	}
}
