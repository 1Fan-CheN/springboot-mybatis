package com.example.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormatUtil {
    public static List<Map<String, Map<String, Object>>> formatInfoById(List<Map<String, Object>> mapList){
        List<Map<String, Map<String, Object>>> newList = new ArrayList<>();
        for(Map<String, Object> map : mapList){
            String id = map.get("id").toString();
            map.remove("id");
            Map<String, Map<String, Object>> newMap = new HashMap<>();
            newMap.put(id, map);
            newList.add(newMap);
        }
        return newList;
    }
}
