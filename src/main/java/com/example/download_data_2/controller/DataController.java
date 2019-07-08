package com.example.download_data_2.controller;

import com.example.download_data_2.entity.Data;
import com.example.download_data_2.service.IDataService;
import com.example.download_data_2.utill.PageInfo;
import com.example.download_data_2.utill.ResultBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@Controller
public class DataController {

    private static String pageSize = "4";

    @Autowired
    private IDataService iDataService;

    /**
     * 爬取某个季度的数据并存入数据库
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("/data")
    @ResponseBody
    public String saveData(HttpServletRequest request) throws IOException {
        String years = request.getParameter("years");
        String jidu = request.getParameter("jidu");
       /* Integer year=Integer.parseInt(years);
        Integer mon=Integer.parseInt(month);*/
        ResultBean result = iDataService.getDataAndSave("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/000001/type/S.phtml?year=" + years + "&jidu=" + jidu);
        if (result.getCode() == 1) {//数据插入数据库成功
            return "成功了";
        }
        //数据插入失败
        return "插入失败";
    }

    /**
     * 爬取某一年的数据并存入数据库
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("/insert")
    @ResponseBody
    //使用cron属性可按照指定时间执行，本例指的是每1分钟执行一次；
    //@Scheduled(cron = "10 * * * * ? ")//方法不让带参数
    public String saveDataByYear(HttpServletRequest request) throws IOException, InterruptedException {
        String years = request.getParameter("years");
        String jidu = request.getParameter("jidu");
       /* Integer year=Integer.parseInt(years);
        Integer mon=Integer.parseInt(month);*/
        if (years.equals("") || years == null) {//判断年份是否为空
            years = "2019";
        }
        ResultBean result = null;
        result = iDataService.findDataAll();
        if (result.getCode() == 0) {//数据库无数据，直接插入数据

            result = insertData(years, jidu, result);//方法抽取
        } else {//数据库有数据，则需要先清空数据库
            result = iDataService.deleteDataAll();
            if (result.getCode() == 1) {//数据已经清空

                result = save_Data(years, jidu, result);
            }
        }
        if (result.getCode() == 1) {//数据插入数据库成功
            return "成功了";
        }
        //数据插入失败
        return "插入失败";
    }

    /**
     * 爬取最近10年的数据
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Scheduled(cron = "10 * * * * ? ")//定时任务，每隔10秒爬取一次近10年的最新数据，这个地方应该使用一个线线程池做多线程处理
    public void getDtaByTenYear() throws InterruptedException, IOException {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        ResultBean result = null;
        result = iDataService.findDataAll();
        if (result.getCode() == 0) {//数据库无数据，直接插入数据

            for (int i = year - 10; i <= year; i++) {//最近10年
                for (int j = 1; j <= 3; j++) {//3个季度

                    result = iDataService.getDataAndSave("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/000001/type/S.phtml?year=" + i + "&jidu=" + j);
                }
                Thread.sleep(10 * 1000);//为有效的防止ip被封的情况，这里将当前线程睡眠30m
            }
        } else {//数据库有数据，则需要先清空数据库
            result = iDataService.deleteDataAll();
            if (result.getCode() == 1) {//数据库数据已经清空

                for (int i = year - 10; i <= year; i++) {//最近10年
                    for (int j = 1; j <= 3; j++) {//3个季度

                        result = iDataService.getDataAndSave("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/000001/type/S.phtml?year=" + i + "&jidu=" + j);
                    }
                    Thread.sleep(10 * 1000);//为有效的防止ip被封的情况，这里将当前线程睡眠30m
                }
            }
        }

        System.out.println(result);
    }

    private ResultBean save_Data(String years, String jidu, ResultBean result) throws IOException, InterruptedException {
        if (jidu == null || jidu.equals("")) {
            jidu = "0";
            for (int i = 1; i <= 3; i++) {
                result = iDataService.getDataAndSave("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/000001/type/S.phtml?year=" + years + "&jidu=" + jidu + i);
                Thread.sleep(10 * 1000);//为有效的防止ip被封的情况，这里将当前线程睡眠30m
            }
        } else {
            result = iDataService.getDataAndSave("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/000001/type/S.phtml?year=" + years + "&jidu=" + jidu);
        }
        return result;
    }

    private ResultBean insertData(String years, String jidu, ResultBean result) throws IOException, InterruptedException {
        result = save_Data(years, jidu, result);
        return result;
    }


    /**
     * 数据前台展示
     *
     * @param pageNo
     * @param
     * @param
     * @return
     */
    @RequestMapping("/list")
    public String dataShow(@Param("pageNo") String pageNo, Model model) {

        if (pageNo == null) {
            pageNo = "1";
        }
        PageInfo<Data> pageInfo = iDataService.dataList(pageNo, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "showdata";
    }

    /**
     * 查询数据接口
     */
    @RequestMapping("/toindex")
    public String toindex() {
        return "index";
    }

}
