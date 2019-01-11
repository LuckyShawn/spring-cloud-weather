package com.shawn.weatherredis.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shawn.weatherredis.service.WeatherDataService;
import com.shawn.weatherredis.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * WeatherDataService 实现.
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

    private static final long TIME_OUT = 10L;

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 根据cityId查询天气数据
     *
     * @param cityId 城市id
     * @return
     */
    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.doGetWeather(uri);
    }

    /**
     * 根据城市名称查询天气数据
     *
     * @param cityName 城市名称
     * @return
     */
    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeather(uri);
    }

    //封装对接口的Get请求
    private WeatherResponse doGetWeather(String uri) {
        WeatherResponse resp = null;
        String strBody = null;
        ObjectMapper mapper = null;
        //检查缓存中是否有天气数据
        if (stringRedisTemplate.hasKey(uri)) {
            logger.info("Redis has data");
            strBody = stringRedisTemplate.opsForValue().get(uri);
        } else {
            //请求接口返回天气信息Entity
            ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
            //返回状态码为200，成功
            if (respString.getStatusCodeValue() == 200) {
                strBody = respString.getBody();
            }
            stringRedisTemplate.opsForValue().set(uri,strBody,TIME_OUT);
        }
        try {
            //将字符串映射成我们想要的WeatherResponse实体类
            mapper = new ObjectMapper();
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            logger.error("Error!",e);
        }
        return resp;
    }

}
