package com.krunal.example.imageandvideodemo;

public class MediaStoreData {
    String path= "",Name = "",Date ="",headerName = "";
    boolean isHeader = false;


    public MediaStoreData() {
    }

    public MediaStoreData(String path, String name, String Date) {
        this.path = path;
        Name = name;
        this.Date = Date;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
