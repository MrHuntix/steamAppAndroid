package com.example.puneeth.steamapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class Details extends AppCompatActivity implements Serializable {

    private TextView gameName,gameDescription,gameLanguages,gameWebsite,gameScore,gameRelease;
    private Game game=null;
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        Intent i=getIntent();
        game=(Game)i.getSerializableExtra("details");
        getSupportActionBar().setTitle(game.getGameName());
        recyclerView=(RecyclerView)findViewById(R.id.activity_game_details_slider);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        imageAdapter=new ImageAdapter(game.getUrls());
        recyclerView.setAdapter(imageAdapter);
        gameName=(TextView)findViewById(R.id.activity_game_details_name);
        gameDescription=(TextView)findViewById(R.id.activity_game_details_description);
        gameLanguages=(TextView)findViewById(R.id.activity_game_details_languages);
        gameWebsite=(TextView)findViewById(R.id.activity_game_details_website);
        gameScore=(TextView)findViewById(R.id.activity_game_details_score);
        gameRelease=(TextView)findViewById(R.id.activity_game_details_release);
        updateViews();
        gameWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(game.getWebsite()));
                startActivity(i);
            }
        });
    }

    public void updateViews(){
        imageAdapter.notifyDataSetChanged();
        gameName.setText(game.getGameName());
        gameDescription.setText(game.getDescription());
        gameLanguages.setText(game.getSupportedLanguages());
        gameWebsite.setText("VISIT US AT: "+game.getWebsite());
        gameScore.setText("METACRITIC SCORE: "+game.getScore());
        gameRelease.setText("RELEASE DATE: "+game.getReleaseDate());
    }
}
