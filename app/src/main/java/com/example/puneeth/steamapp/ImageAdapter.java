package com.example.puneeth.steamapp;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyImageViewHolder> {

    private ArrayList<String> urls;

    @Override
    public MyImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.screenshot,parent,false);
        return new MyImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyImageViewHolder holder, int position) {
        String url=urls.get(position);
        try {
            Uri uri=Uri.parse(url);
            holder.draweeView.setImageURI(uri);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class MyImageViewHolder extends RecyclerView.ViewHolder{
        public SimpleDraweeView draweeView;
        public MyImageViewHolder(View view){
            super(view);
            draweeView= (SimpleDraweeView) view.findViewById(R.id.screenshot_image);
        }
    }

    public ImageAdapter(ArrayList<String> urls){
        this.urls=urls;
    }

}
