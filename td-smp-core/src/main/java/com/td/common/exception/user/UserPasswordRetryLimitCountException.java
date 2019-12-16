package com.td.common.exception.user;

/**
 * @author tudedong
 * @description 用户错误记数异常类
 * @date 2019-12-12 19:10:33
 */
public class UserPasswordRetryLimitCountException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitCountException(int retryLimitCount)
    {
        super("user.password.retry.limit.count", new Object[] { retryLimitCount });
    }
}
