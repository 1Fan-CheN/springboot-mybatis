package com.example.utils;

import java.util.ArrayList;
import java.util.List;

public class ConverseUtil {

    public static List<Object> objToList(Object obj){
        List<Object> result = new ArrayList<>();
        System.out.println(obj.getClass());
        if (obj instanceof List<?>){
            result.addAll((List<?>) obj);
        }
        return result;
    }
}
