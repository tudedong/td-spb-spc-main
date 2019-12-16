package com.td.quartz.util;

import org.quartz.JobExecutionContext;

import com.td.quartz.domain.SysJob;

/**
 * @author tudedong
 * @description 定时任务处理（允许并发执行）
 * @date 2019-12-12 19:10:33
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
