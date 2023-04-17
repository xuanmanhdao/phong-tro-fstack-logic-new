package com.fstack.phong_tro_fstack.base.helper;

import java.util.Date;

public class Common {
    public static Date getCurrenDate() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        return date;
    }
}
