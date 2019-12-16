package com.td.common.exception.user;

/**
 * @author tudedong
 * @description 角色锁定异常类
 * @date 2019-12-12 19:10:33
 */
public class RoleBlockedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public RoleBlockedException()
    {
        super("role.blocked", null);
    }
}
