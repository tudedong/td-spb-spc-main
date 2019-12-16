package com.td.framework.shiro.service;

import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.td.common.constant.Constants;
import com.td.common.constant.ShiroConstants;
import com.td.common.exception.user.UserPasswordNotMatchException;
import com.td.common.exception.user.UserPasswordRetryLimitExceedException;
import com.td.common.utils.MessageUtils;
import com.td.framework.manager.AsyncManager;
import com.td.framework.manager.factory.AsyncFactory;
import com.td.system.domain.SysUser;

/**
 * @author tudedong
 * @description 登录密码方法
 * @date 2019-12-12 19:10:33
 */
@Component
public class SysPasswordService {
	@Autowired
	private CacheManager cacheManager;

	private Cache<String, AtomicInteger> loginRecordCache;

	@Value(value = "${user.password.maxRetryCount}")
	private String maxRetryCount;

	@PostConstruct
	public void init() {
		loginRecordCache = cacheManager.getCache(ShiroConstants.LOGINRECORDCACHE);
	}

	/**
	 * 校验密码方法
	 * @param user 从数据库查询的用户
	 * @param password 页面填写的密码
	 */
	public void validate(SysUser user, String password) {
		String loginName = user.getLoginName();

		AtomicInteger retryCount = loginRecordCache.get(loginName);

		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			loginRecordCache.put(loginName, retryCount);
		}
		if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue()) {
			AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL,
					MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount)));
			throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
		}

		if (!matches(user, password)) {
			AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL,
					MessageUtils.message("user.password.retry.limit.count", retryCount)));
			loginRecordCache.put(loginName, retryCount);
			throw new UserPasswordNotMatchException();
		} else {
			clearLoginRecordCache(loginName);
		}
	}

	/**
	 * 用数据库的密码与登录密码进行匹配
	 * @param user 从数据查询的用户信息
	 * @param newPassword 页面输入的密码
	 * @return
	 */
	public boolean matches(SysUser user, String newPassword) {
		//判断数据库密码（加盐的）和用户登录密码加盐后的结果是否一致
		return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
	}

	public void clearLoginRecordCache(String username) {
		loginRecordCache.remove(username);
	}

	/**
	 * 对页面输入的密码进行加密，加密规则“Md5Hash”，使用字符串为“用户名+密码+盐”
	 * @param username 页面填写的用户名（即数据库用户信息中的用户名）
	 * @param password 页面输入的密码
	 * @param salt 数据库中的盐值
	 * @return
	 */
	public String encryptPassword(String username, String password, String salt) {
		return new Md5Hash(username + password + salt).toHex().toString();
	}

	public static void main(String[] args) {
		String a = new Md5Hash("tutu123456843e01").toHex();
		System.out.println(a);
	}

}
