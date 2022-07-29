package com.huang.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class JacksonUtil {



//    方法重载:如果用户传入的参数只有一个对象则执行上面的方法,如果传入的参数有两个,则执行下面的
    public static String getJson(Object o) throws JsonProcessingException {
        return getJson(o,"yyyy-MM-dd HH:mm:ss");

    }

    public static String getJson(Object o ,String dataFormat) throws JsonProcessingException {


        ObjectMapper mapper = new ObjectMapper();
//        取消Jackson的默认输出日期格式为时间戳的设置
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);

//        设置自定义的时间返回模式
        SimpleDateFormat sdf=new SimpleDateFormat(dataFormat);
        mapper.setDateFormat(sdf);

        return mapper.writeValueAsString(o);
    }
}
