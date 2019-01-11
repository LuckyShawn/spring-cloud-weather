package com.shawn.weatherbasic.controller;

import com.shawn.weatherbasic.service.WeatherDataService;
import com.shawn.weatherbasic.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
