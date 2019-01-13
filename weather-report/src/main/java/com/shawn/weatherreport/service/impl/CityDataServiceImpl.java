package com.shawn.weatherreport.service.impl;


import com.shawn.weatherreport.service.CityDataService;
import com.shawn.weatherreport.utils.XmlBuilder;
import com.shawn.weatherreport.vo.City;
import com.shawn.weatherreport.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


/**
 * @Description: TODO
 * @Author: shawn
 * @Date: 2019/1/13 0013 13:50
 */
@Service
public class CityDataServiceImpl implements CityDataService {

    /**
     * 解析XMl文件获取城市id列表
     * @return
     * @throws Exception
     */
    @Override
    public List<City> listCity() throws Exception {
        // 读取XML文件
        Resource resource = new ClassPathResource("citylist.xml");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
        StringBuffer buffer = new StringBuffer();
        String line = "";

        while ((line = br.readLine()) !=null) {
            buffer.append(line);
        }

        br.close();

        // XML转为Java对象
        CityList cityList = (CityList) XmlBuilder.xmlStrToObject(CityList.class, buffer.toString());
        return cityList.getCityList();
    }
}
