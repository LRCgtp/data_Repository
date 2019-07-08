package com.example.download_data_2.service;

import com.example.download_data_2.entity.Data;
import com.example.download_data_2.utill.PageInfo;
import com.example.download_data_2.utill.ResultBean;
import org.hibernate.validator.constraints.EAN;

import java.io.IOException;
import java.util.List;

public interface IDataService {
     ResultBean getDataAndSave(String url) throws IOException;

     PageInfo<Data> dataList(String pageNo, String pageSize);

     ResultBean deleteDataAll();

     ResultBean findDataAll();

}
