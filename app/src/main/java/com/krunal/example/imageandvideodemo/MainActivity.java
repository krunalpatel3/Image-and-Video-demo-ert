package com.krunal.example.imageandvideodemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.krunal.example.imageandvideodemo.ClsGoble.REQUEST_PERMISSION_ACTION;
import static com.krunal.example.imageandvideodemo.ClsGoble.STORAGE_PERMISSIONS;
import static com.krunal.example.imageandvideodemo.ClsGoble.hasPermissions;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    LinearLayout no_data;

    List<MediaStoreData> list = new ArrayList<>();
    AllAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new AllAdapter(MainActivity.this);
        rv = findViewById(R.id.rv);
        no_data = findViewById(R.id.no_data);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);


        if (hasPermissions(MainActivity.this,STORAGE_PERMISSIONS)){
            getAllMedia();
            fetchGalleryImages();
            if (list.size()>0){
                adapter.AddItems(list);
                no_data.setVisibility(View.GONE);
            }else {
                no_data.setVisibility(View.VISIBLE);
            }

        }else {
            ClsGoble.askPermission(MainActivity.this, 1,
                    STORAGE_PERMISSIONS);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_ACTION: {
                if (ArrayUtils.contains(grantResults, -1)){
                    Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
                }

                return;
            }

        }
    }


    public ArrayList<String> getAllMedia() {
        HashSet<String> videoItemHashSet = new HashSet<>();
        String[] projection = { MediaStore.Video.VideoColumns.DATA ,
                MediaStore.Video.Media.DISPLAY_NAME,MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;//order data by date

        Cursor cursor = getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, orderBy);
        try {
            cursor.moveToFirst();
            do{
                videoItemHashSet.add((
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))));
                ;
                list.add(new MediaStoreData(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)),
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))));

            }while(cursor.moveToNext());

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> downloadedList = new ArrayList<>(videoItemHashSet);

        Gson gson = new Gson();
        String jsonInString = gson.toJson(downloadedList);
        Log.d("Result", "downloadedList- " + jsonInString);

        Log.d("Result", "downloadedList- " + downloadedList.size());
        return downloadedList;
    }

    public ArrayList<String> fetchGalleryImages() {
        ArrayList<String> galleryImageUrls;
        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME};//get all columns of type images
        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;//order data by date

        Cursor imagecursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy + " DESC");//get all data in Cursor by sorting in DESC order

        galleryImageUrls = new ArrayList<String>();

        for (int i = 0; i < imagecursor.getCount(); i++) {
            imagecursor.moveToPosition(i);
            int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);//get column index
            galleryImageUrls.add(imagecursor.getString(dataColumnIndex));//get Image from column index
            list.add(new MediaStoreData(imagecursor.getString(imagecursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)),
                    imagecursor.getString(imagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))));

        }

        Gson gson = new Gson();
        String jsonInString = gson.toJson(list);
        Log.d("Result", "downloadedList- " + jsonInString);
        Log.d("Result", "downloadedList- " + list.size());

        Log.e("fatch in","images");
        return galleryImageUrls;
    }

}