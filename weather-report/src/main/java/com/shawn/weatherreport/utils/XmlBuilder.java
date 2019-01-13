package com.shawn.weatherreport.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;

/**
 * @Description: Xml工具类
 * @Author: shawn
 * @Date: 2019/1/13 0013 13:43
 */
public class XmlBuilder {
    /**
     * 讲xml转化为指定的POJO对象
     * @return
     */
    public static Object xmlStrToObject(Class<?> clazz, String xmlStr) throws Exception {
        Object xmlObject = null;
        Reader reader = null;
        JAXBContext context = JAXBContext.newInstance(clazz);

        //xml转换成对象的接口
        Unmarshaller unmarshaller = context.createUnmarshaller();
        reader = new StringReader(xmlStr);
        xmlObject = unmarshaller.unmarshal(reader);
        if(null != reader){
            reader.close();
        }
        return xmlObject;
    }
}
