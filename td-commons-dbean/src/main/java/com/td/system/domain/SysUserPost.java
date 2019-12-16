package com.td.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tudedong
 * @description 用户和岗位关联 sys_user_post
 * @date 2019-12-12 19:10:33
 */
@Getter
@Setter
public class SysUserPost {
	/** 用户ID */
	private Long userId;

	/** 岗位ID */
	private Long postId;

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("userId", getUserId())
				.append("postId", getPostId()).toString();
	}
}
