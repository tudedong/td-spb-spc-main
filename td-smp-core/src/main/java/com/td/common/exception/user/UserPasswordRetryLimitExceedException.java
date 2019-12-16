package com.td.common.exception.user;

/**
 * @author tudedong
 * @description 用户错误最大次数异常类
 * @date 2019-12-12 19:10:33
 */
public class UserPasswordRetryLimitExceedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitExceedException(int retryLimitCount)
    {
        super("user.password.retry.limit.exceed", new Object[] { retryLimitCount });
    }
}
