package com.td.common.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tudedong
 * @description Entity基类
 * @date 2019-12-12 19:10:33
 */
@Getter
@Setter
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 搜索值 */
	@Transient
	private String searchValue;

	/** 创建者 */
	@Transient
	private String createBy;

	/** 创建时间 */
	@Transient
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/** 更新者 */
	@Transient
	private String updateBy;

	/** 更新时间 */
	@Transient
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/** 备注 */
	@Transient
	private String remark;

	/** 请求参数 */
	@Transient
	private Map<String, Object> params;

	public Map<String, Object> getParams() {
		if (params == null) {
			params = new HashMap<>();
		}
		return params;
	}
}
