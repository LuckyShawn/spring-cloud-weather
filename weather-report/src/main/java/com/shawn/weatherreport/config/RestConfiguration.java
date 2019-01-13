package com.shawn.weatherreport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @Description 配置RestTemplate
 * @Authod shawn
 * @create 2019/1/11 0011
 */
@Configuration
public class RestConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        //用HttpClient作为rest客户端
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        //支持中文编码
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

}
