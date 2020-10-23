package com.krunal.example.imageandvideodemo;

public class MediaStoreData {
    String path= "",Name = "";

    public MediaStoreData(String path, String name) {
        this.path = path;
        Name = name;
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
