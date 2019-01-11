package com.shawn.weatherbasic.service.impl;

import java.io.IOException;

import com.shawn.weatherbasic.service.WeatherDataService;
import com.shawn.weatherbasic.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

/**
 * WeatherDataService 实现.
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
	
	@Autowired
	private RestTemplate restTemplate;

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
		//请求接口返回天气信息Entity
 		ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		WeatherResponse resp = null;
		String strBody = null;
		//返回状态码为200，成功
		if (respString.getStatusCodeValue() == 200) {
			strBody = respString.getBody();
		}
		try {
			//将字符串映射成我们想要的WeatherResponse实体类
			resp = mapper.readValue(strBody, WeatherResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}

}
