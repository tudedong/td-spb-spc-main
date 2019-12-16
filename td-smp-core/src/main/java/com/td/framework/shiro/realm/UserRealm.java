package com.td.framework.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.td.common.exception.user.CaptchaException;
import com.td.common.exception.user.RoleBlockedException;
import com.td.common.exception.user.UserBlockedException;
import com.td.common.exception.user.UserNotExistsException;
import com.td.common.exception.user.UserPasswordNotMatchException;
import com.td.common.exception.user.UserPasswordRetryLimitExceedException;
import com.td.framework.shiro.service.SysLoginService;
import com.td.framework.util.ShiroUtils;
import com.td.system.domain.SysUser;
import com.td.system.service.ISysMenuService;
import com.td.system.service.ISysRoleService;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author tudedong
 * @description 自定义Realm 处理登录认证和权限
 * @date 2019-12-12 19:10:33
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

	@Resource
	private ISysMenuService menuService;

	@Resource
	private ISysRoleService roleService;

	@Resource
	private SysLoginService loginService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SysUser user = ShiroUtils.getSysUser();
		// 角色列表
		Set<String> roles = new HashSet<String>();
		// 功能列表
		Set<String> menus = new HashSet<String>();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 管理员拥有所有权限
		if (user.isAdmin()) {
			info.addRole("admin");
			info.addStringPermission("*:*:*");
		} else {
			roles = roleService.selectRoleKeys(user.getUserId());
			menus = menuService.selectPermsByUserId(user.getUserId());
			// 角色加入AuthorizationInfo认证对象
			info.setRoles(roles);
			// 权限加入AuthorizationInfo认证对象
			info.setStringPermissions(menus);
		}
		return info;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.将AuthenticationToken转换为UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		//2.从UsernamePasswordToken中获取username
		String username = upToken.getUsername();
		String password = "";
		if (upToken.getPassword() != null) {
			password = new String(upToken.getPassword());
		}
		SysUser user = null;
		try {
			//3.调用service方法，从数据库username对应的用户记录
			user = loginService.login(username, password);
			//4.根据用户的情况，抛出各种异常
		} catch (CaptchaException e) {
			//抛出验证码失败异常
			throw new AuthenticationException(e.getMessage(), e);
		} catch (UserNotExistsException e) {
			//抛出用户不存在异常
			throw new UnknownAccountException(e.getMessage(), e);
		} catch (UserPasswordNotMatchException e) {
			//抛出用户密码不正确或不符合规范异常
			throw new IncorrectCredentialsException(e.getMessage(), e);
		} catch (UserPasswordRetryLimitExceedException e) {
			//抛出用户错误最大次数异常
			throw new ExcessiveAttemptsException(e.getMessage(), e);
		} catch (UserBlockedException e) {
			//抛出用户锁定异常
			throw new LockedAccountException(e.getMessage(), e);
		} catch (RoleBlockedException e) {
			//抛出角色锁定异常类
			throw new LockedAccountException(e.getMessage(), e);
		} catch (Exception e) {
			//抛出未知异常
			log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
			throw new AuthenticationException(e.getMessage(), e);
		}
		//5.若没有异常，则根据用户信息构建SimpleAuthenticationInfo对象并返回
		//注意以下信息是从数据库中获取的：
		//1.user:认证的实体信息，可以是username、也可以是username对应的实体类对象
		//2.password：认证后的密码
		//3.realmName：当前realm对象的name，调用父类的getName()方法即可
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

	/**
	 * 清理缓存权限
	 */
	public void clearCachedAuthorizationInfo() {
		this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}
}
