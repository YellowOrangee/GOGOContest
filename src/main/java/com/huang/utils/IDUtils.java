package com.huang.utils;

import java.util.UUID;

//通过UUID类获取随机id
public class IDUtils {
    public static String getId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
