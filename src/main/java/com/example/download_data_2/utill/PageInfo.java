package com.example.download_data_2.utill;

import java.io.Serializable;
import java.util.List;

public class PageInfo<T> implements Serializable {
    private Integer pageNo;//当前页
    private Integer pageSize;//每页返回的条数
    private Integer count;//总记录数
    private Integer pageCount;//总页数
    private List<T> t;//封装数据

    public PageInfo(Integer pageNo, Integer pageSize, Integer count, Integer pageCount, List<T> t) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.count = count;
        this.pageCount = pageCount;
        this.t = t;
    }

    public PageInfo() {
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getT() {
        return t;
    }

    public void setT(List<T> t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", count=" + count +
                ", pageCount=" + pageCount +
                ", t=" + t +
                '}';
    }
}
