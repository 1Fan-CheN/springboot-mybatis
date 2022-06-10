package com.example.utils;

import java.sql.Timestamp;
import java.util.Date;

public class TimeUtil {
    public static Timestamp getNowTs() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
}
