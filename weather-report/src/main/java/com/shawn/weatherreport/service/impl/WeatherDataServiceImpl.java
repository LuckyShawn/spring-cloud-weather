package com.shawn.weatherreport.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shawn.weatherreport.service.WeatherDataService;
import com.shawn.weatherreport.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * WeatherDataService 实现.
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
	private Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

	private static final long TIME_OUT = 1800L; // 1800s
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 根据cityId查询天气数据
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
		//检查redis是否存在key
		if (stringRedisTemplate.hasKey(uri)){
			logger.info("Redis has data");
			strBody = stringRedisTemplate.opsForValue().get(uri);
		}else{
			//请求接口返回天气信息Entity
			ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
			//返回状态码为200，成功
			if (respString.getStatusCodeValue() == 200) {
				strBody = respString.getBody();
				//存入redis吗 1800s超时
				stringRedisTemplate.opsForValue().set(uri,strBody,TIME_OUT, TimeUnit.SECONDS);
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			//将字符串映射成我们想要的WeatherResponse实体类
			resp = mapper.readValue(strBody, WeatherResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}

	/**
	 * 把天气数据放在缓存
	 * @param cityId
	 */
	@Override
	public void syncDateByCityId(String cityId) {
		String uri = WEATHER_URI + "citykey=" + cityId;
		this.saveWeatherData(uri);
	}

	private void saveWeatherData(String uri) {
		String key = uri;
		String strBody = null;
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

		// 调用服务接口来获取
		ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

		if (respString.getStatusCodeValue() == 200) {
			strBody = respString.getBody();
		}

		// 数据写入缓存
		ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);

	}

}
