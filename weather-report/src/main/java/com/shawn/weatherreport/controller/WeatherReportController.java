package com.shawn.weatherreport.controller;

import com.shawn.weatherreport.service.CityDataService;
import com.shawn.weatherreport.service.WeatherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description 天气预报接口
 * @Authod shawn
 * @create 2019/1/13 0011
 */
@RestController
@RequestMapping("/report")
public class WeatherReportController {
	@Autowired
	private CityDataService cityDataService;
	
	@Autowired
	private WeatherReportService weatherReportService;

	@GetMapping("/index")
	public ModelAndView index(Model model) throws Exception {
		model.addAttribute("title","实时天气");
		model.addAttribute("cityList", cityDataService.listCity());
		return new ModelAndView("weather/index","reportModel",model);
	}


	@GetMapping("/cityId/{cityId}")
	public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {

		model.addAttribute("title","实时天气");
		model.addAttribute("cityId",cityId);
		model.addAttribute("cityList", cityDataService.listCity());
		model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
		return new ModelAndView("weather/report","reportModel",model);
	}

}
