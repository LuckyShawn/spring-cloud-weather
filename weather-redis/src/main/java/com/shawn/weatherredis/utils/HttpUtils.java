package com.shawn.weatherredis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @Description TODO
 * @Authod shawn
 * @create 2019/1/11 0011
 */
public class HttpUtils {

    @Autowired
    private static RestTemplate restTemplate;

    //封装对接口的Get请求
//    public static WeatherResponse doGetWeahter(String uri) {
//        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
//        ObjectMapper mapper = new ObjectMapper();
//        WeatherResponse resp = null;
//        String strBody = null;
//
//        if (respString.getStatusCodeValue() == 200) {
//            strBody = respString.getBody();
//        }
//
//        try {
//            resp = mapper.readValue(strBody, WeatherResponse.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return resp;
//    }
}
