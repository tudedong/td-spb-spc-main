package com.td.common.exception.user;

/**
 * @author tudedong
 * @description 用户不存在异常类
 * @date 2019-12-12 19:10:33
 */
public class UserNotExistsException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserNotExistsException()
    {
        super("user.not.exists", null);
    }
}
