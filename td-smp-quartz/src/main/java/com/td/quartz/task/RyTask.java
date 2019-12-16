package com.td.quartz.task;

import org.springframework.stereotype.Component;

/**
 * @author tudedong
 * @description 定时任务调度测试
 * @date 2019-12-12 19:10:33
 */
@Component("ryTask")
public class RyTask
{
    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }
}
