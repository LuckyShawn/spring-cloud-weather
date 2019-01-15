package com.shawn.weatherreportserver.controller;


import com.shawn.weatherreportserver.service.WeatherReportService;
import com.shawn.weatherreportserver.vo.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 天气预报接口
 * @Authod shawn
 * @create 2019/1/13 0011
 */
@RestController
@RequestMapping("/report")
public class WeatherReportController {
	private final static Logger logger = LoggerFactory.getLogger(WeatherReportController.class);

	@Autowired
	private WeatherReportService weatherReportService;

	@GetMapping("/index")
	public ModelAndView index(Model model) throws Exception {
		List<City> cityList = null;
		// TODO 改为由城市数据API微服务提供数据
		cityList = new ArrayList<>();
		City city = new City();
		city.setCityId("101280601");
		city.setCityName("深圳");
		cityList.add(city);
		model.addAttribute("title","实时天气");
		model.addAttribute("cityList", cityList);
		return new ModelAndView("weather/index","reportModel",model);
	}

	@GetMapping("/cityId/{cityId}")
	public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {
		// 获取城市ID列表
		// TODO 改为由城市数据API微服务来提供数据
		List<City> cityList = null;

		try {

			// TODO 改为由城市数据API微服务提供数据
			cityList = new ArrayList<>();
			City city = new City();
			city.setCityId("101280601");
			city.setCityName("深圳");
			cityList.add(city);

		} catch (Exception e) {
			logger.error("Exception!", e);
		}

		model.addAttribute("title", "天气预报");
		model.addAttribute("cityId", cityId);
		model.addAttribute("cityList", cityList);
		model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
		return new ModelAndView("weather/report", "reportModel", model);

	}
}
