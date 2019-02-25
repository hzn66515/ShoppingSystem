package com.hzn.sales.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdGeneral {
    private static long orderNum = 0l;
    private static String date ;
    private static int goodId = 0;
    public synchronized static Long getOrderNo() {
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        if(date==null||!date.equals(str)){
            date = str;
            orderNum  = 0l;
        }
        orderNum ++;
        long orderNo = Long.parseLong((date)) * 10000;
        orderNo += orderNum;;
        return orderNo;
    }

    public synchronized  static int getGoodId() {
        goodId ++;
        return goodId;
    }
}
