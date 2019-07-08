package com.example.download_data_2.utill;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeStringToDate {

    public static Date dateUtill(String date){
        //创建SimpleDateFormat对象实例并定义好转换格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //System.out.println("把当前时间转换成字符串：" + sdf.format(new Date()));

        Date d = null;
        try {
            // 注意格式需要与上面一致，不然会出现异常
            d = sdf.parse(date);
        } catch (Exception e) {

        //System.out.println("字符串转换成时间:" + d);
    }
        return d;
    }

    public static void main(String[] args) {
        //创建SimpleDateFormat对象实例并定义好转换格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //System.out.println("把当前时间转换成字符串：" + sdf.format(new Date()));

        Date dates = null;
        try {
            // 注意格式需要与上面一致，不然会出现异常
            dates = sdf.parse("2019-07-01");
        } catch (Exception e) {


        }
        System.out.println("字符串转换成时间:" +dates);
    }
}
