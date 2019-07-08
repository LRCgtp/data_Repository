package com.example.download_data_2.dao;

import com.example.download_data_2.entity.Data;
import com.example.download_data_2.utill.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataDao {
    void saveData(@Param("datas") List<Data> datas);

    List<Data> findDataAll();

    Integer getCount();

    void deleteDatabase();
}
