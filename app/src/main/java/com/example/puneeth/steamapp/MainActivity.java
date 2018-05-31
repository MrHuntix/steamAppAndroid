package com.example.puneeth.steamapp;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements Serializable{

    private List<Game> gamesList= new ArrayList<>();
    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("HOME");
        Fresco.initialize(this);
        recyclerView=(RecyclerView) findViewById(R.id.activity_main_recycler_view);
        gameAdapter=new GameAdapter(gamesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(gameAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("2","Name: "+gamesList.get(position).getGameName());
                Intent intent=new Intent(MainActivity.this,Details.class);
                intent.putExtra("details", (Serializable) gamesList.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));
        prepareGameData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:
                Log.d("2","Logged out");
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public Game parseDate(JSONObject response) throws JSONException{
            Game game=null;
            String score="Not yet rated";
            String name=response.getString("name");
            //String description=response.getString("description").replace("<strong>", "").replace("</strong>", "").replace("<br>", "").replace("</br>", "").replace(".", ". ").replace("  ", " ").trim();
            String description=response.getString("description");
            description=Html.fromHtml(description).toString().replaceAll("\n","").trim().replaceAll("\\s+", " ");
            String supportedLanguages=response.getString("supported_languages");//.replace("<strong>*</strong>", "").replace("<strong>", "").replace("</strong>", "").replace("<br>", "").replace("</br>", "").replace("languages with full audio support",".").trim();
            supportedLanguages=Html.fromHtml(supportedLanguages).toString().replaceAll("\n","").replace("*","").trim().replaceAll("\\s+", " ");
            String image_url=response.getString("header_image");
            String website=response.getString("website");
            if(!response.isNull("metacritic")){
                score=response.getJSONObject("metacritic").getString("score");
            }

            JSONArray thumbnails=response.getJSONArray("screenshots");
            ArrayList<String> urls=new ArrayList<>();
            for(int i=0;i<thumbnails.length();i++){
                JSONObject c=thumbnails.getJSONObject(i);
                urls.add(c.getString("path_thumbnail"));
            }
            //Log.d("2",urls.toString());
            String releaseDate=response.getJSONObject("release_date").getString("date");
            game=new Game(name,String.valueOf(new Random().nextInt(1000)%5000),image_url,description,score,supportedLanguages,website,releaseDate,urls);

        return game;
    }
    public void prepareGameData(){
        WifiManager wm=(WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip= android.text.format.Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        String url="https://serene-inlet-58681.herokuapp.com/api/all";
        JsonObjectRequest stringRequest=new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                gamesList.clear();
                try {
                    Log.d("2","response");
                    gamesList.add(parseDate(response.getJSONObject("g1")));
                    gamesList.add(parseDate(response.getJSONObject("g2")));
                    gamesList.add(parseDate(response.getJSONObject("g3")));
                    gamesList.add(parseDate(response.getJSONObject("g4")));
                    gamesList.add(parseDate(response.getJSONObject("g5")));
                    gamesList.add(parseDate(response.getJSONObject("g6")));
                    gamesList.add(parseDate(response.getJSONObject("g7")));
                    gamesList.add(parseDate(response.getJSONObject("g8")));
                    Log.d("2","end");
                    gameAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"NO INTERNET ACCESS",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("2",error.getMessage());
            }
        });

        Controller.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }


}
