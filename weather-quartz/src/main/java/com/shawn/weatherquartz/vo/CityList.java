package com.shawn.weatherquartz.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * City List.
 */
@XmlRootElement(name = "c")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@ToString
public class CityList {
	@XmlElement(name = "d")
	private List<City> cityList;

}
