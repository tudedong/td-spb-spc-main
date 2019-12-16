package com.td.common.enums;

/**
 * @author tudedong
 * @description 用户会话
 * @date 2019-12-12 19:10:33
 */
public enum OnlineStatus
{
    /** 用户状态 */
    on_line("在线"), off_line("离线");

    private final String info;

    private OnlineStatus(String info)
    {
        this.info = info;
    }

    public String getInfo()
    {
        return info;
    }
}
