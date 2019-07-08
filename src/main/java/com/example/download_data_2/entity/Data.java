package com.example.download_data_2.entity;

import java.io.Serializable;
import java.util.Date;

public class Data implements Serializable {
    private Integer id;
    private Date date;
    private Double open;
    private Double height;
    private Double close;
    private Double lower;
    private Long jiaoyiliang;
    private Long changemoney;

    public Data(Date date, Double open, Double height, Double close, Double lower, Long jiaoyiliang, Long changemoney, Integer id) {
        this.date = date;
        this.open = open;
        this.height = height;
        this.close = close;
        this.lower = lower;
        this.jiaoyiliang = jiaoyiliang;
        this.changemoney = changemoney;
        this.id = id;
    }

    public Data() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getLower() {
        return lower;
    }

    public void setLower(Double lower) {
        this.lower = lower;
    }

    public Long getJiaoyiliang() {
        return jiaoyiliang;
    }

    public void setJiaoyiliang(Long jiaoyiliang) {
        this.jiaoyiliang = jiaoyiliang;
    }

    public Long getChangemoney() {
        return changemoney;
    }

    public void setChangemoney(Long changemoney) {
        this.changemoney = changemoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", date=" + date +
                ", open=" + open +
                ", height=" + height +
                ", close=" + close +
                ", lower=" + lower +
                ", jiaoyiliang=" + jiaoyiliang +
                ", changemoney=" + changemoney +
                '}';
    }
}
