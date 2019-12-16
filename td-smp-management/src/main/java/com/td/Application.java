package com.td;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.td.common.core.controller.MyHttpMessageConverter;

/**
 * @author tudedong
 * @description 启动程序
 * @date 2019-12-12 19:10:33
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@PropertySource(value = "classpath:config-dev.properties")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// 编解码配置
	@Bean
	public HttpMessageConverters MyHttpMessageConverters() {
		MyHttpMessageConverter fastConverter = new MyHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteDateUseDateFormat);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}

}