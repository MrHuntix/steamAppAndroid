package com.example.puneeth.steamapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder>{

    private List<Game> gamesList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name,players;
        public SimpleDraweeView draweeView;
        public MyViewHolder(View view){
            super(view);
            name=(TextView)view.findViewById(R.id.game_row_name);
            players=(TextView)view.findViewById(R.id.game_row_players);
            draweeView= (SimpleDraweeView) view.findViewById(R.id.game_row_image);
        }
    }

    public GameAdapter(List<Game> gamesList){
        Log.d("2","game adapter");
        this.gamesList=gamesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_row,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Game game=gamesList.get(position);
        Log.d("2",game.getImage_url());
        try {
            holder.name.setText(game.getGameName());
            holder.players.setText(game.getNumberPlayer());
            Uri uri= Uri.parse(game.getImage_url());
            holder.draweeView.setImageURI(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }
}
