package com.example.download_data_2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(value = "com.example.download_data_2.dao")
@EnableScheduling
@EnableAsync
public class DownloadData2Application {

    public static void main(String[] args) {
        SpringApplication.run(DownloadData2Application.class, args);
    }

}
