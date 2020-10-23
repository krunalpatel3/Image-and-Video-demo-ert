package com.krunal.example.imageandvideodemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.Myviewholder> {

    List<MediaStoreData> list = new ArrayList<>();

    Context context;

    public AllAdapter(Context context){
this.context =context;
    }


   public void AddItems( List<MediaStoreData>  list){
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        Myviewholder myViewHolder = new Myviewholder(itemview);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        MediaStoreData current = list.get(position);
        Glide.with(context)
                .load(current.getPath())
                .apply(new RequestOptions().centerCrop())
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Myviewholder extends RecyclerView.ViewHolder {

        ImageView image;

        Myviewholder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);


        }


    }

}
