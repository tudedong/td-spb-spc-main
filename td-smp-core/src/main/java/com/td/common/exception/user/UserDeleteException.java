package com.td.common.exception.user;

/**
 * @author tudedong
 * @description 用户账号已被删除
 * @date 2019-12-12 19:10:33
 */
public class UserDeleteException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserDeleteException()
    {
        super("user.password.delete", null);
    }
}
