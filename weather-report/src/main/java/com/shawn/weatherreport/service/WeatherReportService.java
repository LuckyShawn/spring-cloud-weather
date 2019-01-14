package com.shawn.weatherreport.service;

import com.shawn.weatherreport.vo.Weather;

/**
 * @Description TODO
 * @Authod shawn
 * @create 2019/1/13 0013
 */
public interface WeatherReportService {

    /**
     * 根据城市id查询天气信息
     * @param cityId
     * @return
     */
    Weather getDataByCityId(String cityId);
}
