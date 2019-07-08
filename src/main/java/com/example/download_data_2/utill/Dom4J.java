package com.example.download_data_2.utill;

import com.example.download_data_2.entity.Data;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Dom4J<T> {
  /*  public static void main(String[] args) throws IOException, DocumentException, ParseException {
        // List<> list = new ArrayList<>();
        //构建httpClient对象
        CloseableHttpClient client = HttpClientBuilder.create().build();

        //构建一个get请求
        HttpGet httpGet = new HttpGet("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/000001/type/S.phtml");
        httpGet.addHeader("Content-Type", "application/json");
        //发送请求
        CloseableHttpResponse response = client.execute(httpGet);
        //获得响应体
        HttpEntity entity = response.getEntity();
        //从响应体中解析响应结果
        String result = EntityUtils.toString(entity);
        //System.out.println(result);

        //3.使用根元素的对象  对其他元素操作操作
        //3.1获取所有的子元素对象 book
        org.jsoup.nodes.Document doc = Jsoup.parse(result);
        Elements t=doc.select("table[id='FundHoldSharesTable']");
        //获取日期
        Elements date = t.select("a[target='_blank']");
        System.out.println("日期对象"+date.text());
       // System.out.println(t.first().text());
        System.out.println("TABLE元素"+t);
        String[] times=date.text().split(" ");
        Data data=null;
        List<Data> list=new ArrayList<>();
        for (int i = 0; i < times.length; i++) {
            //System.out.println(times[i]);
            data=new Data();
            data.setDate(ChangeStringToDate.dateUtill(times[i]));
            list.add(data);
        }
  //开盘数据
        //t=t.select("thead,tr,th").remove();
        t.select("strong").remove();
        t=t.select("tr,td,div,a").remove();
        String op = t.select("div[align='center']").text();
        //String opp=op.substring(3,op.length()-1);
        System.out.println("开盘数据"+op);
        String[] opens=op.split(" ");
        int i;
        for ( i = 0; i < opens.length; i++) {

            System.out.println(opens[i]);
            for (int i1 = 0; i1 < list.size(); i1++) {
                list.get(i1).setOpen(Double.parseDouble(opens[0]));
                list.get(i1).setHeight(Double.parseDouble(opens[1]));
                list.get(i1).setClose(Double.parseDouble(opens[2]));
                list.get(i1).setLower(Double.parseDouble(opens[3]));
                list.get(i1).setJiaoyiliang(Long.parseLong(opens[4]));
                list.get(i1).setChangemoney(Long.parseLong(opens[5]));
            }
        }
        for (int m = 0; m < list.size(); m++) {
            System.out.println(list.get(m));
        }
    }*/

    public static List<Data> getdada(String url) throws IOException {
        // List<> list = new ArrayList<>();
        //构建httpClient对象
        CloseableHttpClient client = HttpClientBuilder.create().build();

        //构建一个get请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-Type", "application/json");
        //发送请求
        CloseableHttpResponse response = client.execute(httpGet);
        //获得响应体
        HttpEntity entity = response.getEntity();
        //从响应体中解析响应结果
        String result = EntityUtils.toString(entity);
        //System.out.println(result);

        //3.使用根元素的对象  对其他元素操作操作
        //3.1获取所有的子元素对象 book
        org.jsoup.nodes.Document doc = Jsoup.parse(result);
        Elements t=doc.select("table[id='FundHoldSharesTable']");
        //获取日期
        Elements date = t.select("a[target='_blank']");
        System.out.println("日期对象"+date.text());
        // System.out.println(t.first().text());
        System.out.println("TABLE元素"+t);
        String[] times=date.text().split(" ");
        Data data=null;
        List<Data> list=new ArrayList<>();
        for (int i = 0; i < times.length; i++) {
            //System.out.println(times[i]);
            data=new Data();
            data.setDate(ChangeStringToDate.dateUtill(times[i]));
            list.add(data);
        }
        //开盘数据
        //t=t.select("thead,tr,th").remove();
        t.select("strong").remove();
        t=t.select("tr,td,div,a").remove();
        String op = t.select("div[align='center']").text();
        //String opp=op.substring(3,op.length()-1);
        System.out.println("开盘数据"+op);
        String[] opens=op.split(" ");
        int i;
        for ( i = 0; i < opens.length; i++) {

            System.out.println(opens[i]);
            for (int i1 = 0; i1 < list.size(); i1++) {
                list.get(i1).setOpen(Double.parseDouble(opens[0]));
                list.get(i1).setHeight(Double.parseDouble(opens[1]));
                list.get(i1).setClose(Double.parseDouble(opens[2]));
                list.get(i1).setLower(Double.parseDouble(opens[3]));
                list.get(i1).setJiaoyiliang(Long.parseLong(opens[4]));
                list.get(i1).setChangemoney(Long.parseLong(opens[5]));
            }
        }
        for (int m = 0; m < list.size(); m++) {
            System.out.println(list.get(m));
        }
        return list;
    }

}
