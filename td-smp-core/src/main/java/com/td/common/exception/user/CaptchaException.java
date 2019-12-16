package com.td.common.exception.user;

/**
 * @author tudedong
 * @description 验证码错误异常类
 * @date 2019-12-12 19:10:33
 */
public class CaptchaException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaException()
    {
        super("user.jcaptcha.error", null);
    }
}
