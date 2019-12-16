package com.td.common.config;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import com.td.common.utils.ServletUtils;

/**
 * @author tudedong
 * @description 服务相关配置
 * @date 2019-12-12 19:10:33
 */
@Component
public class ServerConfig
{
    /**
     * 获取完整的请求路径，包括：域名，端口，上下文访问路径
     * 
     * @return 服务地址
     */
    public String getUrl()
    {
        HttpServletRequest request = ServletUtils.getRequest();
        return getDomain(request);
    }

    public static String getDomain(HttpServletRequest request)
    {
        StringBuffer url = request.getRequestURL();
        String contextPath = request.getServletContext().getContextPath();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }
}
