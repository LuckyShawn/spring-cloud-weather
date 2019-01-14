package com.shawn.weatherreport.service.impl;

import com.shawn.weatherreport.service.WeatherDataService;
import com.shawn.weatherreport.service.WeatherReportService;
import com.shawn.weatherreport.vo.Weather;
import com.shawn.weatherreport.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 天气预报接口
 * @Authod shawn
 * @create 2019/1/13 0013
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {
    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    public Weather getDataByCityId(String cityId) {
        WeatherResponse resp = weatherDataService.getDataByCityId(cityId);
        return resp.getData();
    }
}
