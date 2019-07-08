package com.example.download_data_2.service.imp;

import com.example.download_data_2.dao.DataDao;
import com.example.download_data_2.entity.Data;
import com.example.download_data_2.service.IDataService;
import com.example.download_data_2.utill.Dom4J;
import com.example.download_data_2.utill.PageInfo;
import com.example.download_data_2.utill.ResultBean;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DataServiceImp implements IDataService {

    @Autowired
    private DataDao dataDao;

    @Override
    public ResultBean getDataAndSave(String url) throws IOException {
        try {
            List<Data> datalist = Dom4J.getdada(url);
            dataDao.saveData(datalist);
            return new ResultBean(1, "数据插入成功!", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean(0, "数据插入失败!", null);
        }
    }

    @Override
    public PageInfo<Data> dataList(String pageNo, String pageSize) {
        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        List<Data> list=dataDao.findDataAll();
        Integer count=dataDao.getCount();
        PageInfo<Data> pageInfo=new PageInfo<>();
        pageInfo.setCount(count);
        Integer pageCount;
        Integer paesize=Integer.parseInt(pageSize);
        Integer pageno=Integer.parseInt(pageNo);
        if (count % paesize ==0){
            pageCount=count / paesize;
        } else {
          pageCount=count / paesize + 1;
        }
        pageInfo.setPageCount(pageCount);
        pageInfo.setPageNo(pageno);
        pageInfo.setT(list);
        return pageInfo;
    }

    @Override
    public ResultBean deleteDataAll() {
        try{
            dataDao.deleteDatabase();
            return new ResultBean(1,"删除成功",null);

        }catch (Exception e){
            return new ResultBean(0,"删除失败",null);
        }

    }

    @Override
    public ResultBean findDataAll() {
        List<Data> dataAll = dataDao.findDataAll();
        if (dataAll.size() !=0){
            return new ResultBean(1,"数据库有数据",null);
        }
        return new ResultBean(0,"数据库无数据",null);
    }
}

