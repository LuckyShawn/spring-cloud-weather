package com.shawn.weatherquartz.controller;


import com.shawn.weatherquartz.service.WeatherDataService;
import com.shawn.weatherquartz.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 天气接口
 * @Authod shawn
 * @create 2019/1/11 0011
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherDataService weatherDataService;

    @GetMapping("/cityId/{cityId}")
    public WeatherResponse getDataByCityId(@PathVariable("cityId") String cityId){
        return  weatherDataService.getDataByCityId(cityId);
    }


    @GetMapping("/cityName")
    public WeatherResponse getDataByCityName(String cityName){
        return  weatherDataService.getDataByCityName(cityName);
    }
}
