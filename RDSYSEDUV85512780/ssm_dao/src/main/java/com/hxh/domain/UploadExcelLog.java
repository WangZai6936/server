package com.hxh.domain;

import javax.xml.crypto.Data;

public class UploadExcelLog {
    String user;
    String excelName;
    Data uploadTime;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public Data getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Data uploadTime) {
        this.uploadTime = uploadTime;
    }
}
