package com.td.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tudedong
 * @description 用户和角色关联 sys_user_role
 * @date 2019-12-12 19:10:33
 */
@Getter
@Setter
public class SysUserRole {
	/** 用户ID */
	private Long userId;

	/** 角色ID */
	private Long roleId;

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("userId", getUserId())
				.append("roleId", getRoleId()).toString();
	}
}
