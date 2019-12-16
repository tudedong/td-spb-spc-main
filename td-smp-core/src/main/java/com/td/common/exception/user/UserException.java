package com.td.common.exception.user;

import com.td.common.exception.base.BaseException;

/**
 * @author tudedong
 * @description 用户信息异常类
 * @date 2019-12-12 19:10:33
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
