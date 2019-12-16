package com.td.common.exception.user;

/**
 * @author tudedong
 * @description 用户密码不正确或不符合规范异常类
 * @date 2019-12-12 19:10:33
 */
public class UserPasswordNotMatchException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException()
    {
        super("user.password.not.match", null);
    }
}
