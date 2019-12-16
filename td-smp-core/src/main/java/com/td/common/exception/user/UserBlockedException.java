package com.td.common.exception.user;

/**
 * @author tudedong
 * @description 用户锁定异常类
 * @date 2019-12-12 19:10:33
 */
public class UserBlockedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserBlockedException()
    {
        super("user.blocked", null);
    }
}
