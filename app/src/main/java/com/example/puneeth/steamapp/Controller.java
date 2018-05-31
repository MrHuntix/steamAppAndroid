package com.example.puneeth.steamapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Controller {

    public static Controller controller;
    private RequestQueue requestQueue;
    private static Context ctx;

    private Controller(Context ctx){
        this.ctx=ctx;
        this.requestQueue=getRequestQueue();
    }

    public static synchronized Controller getInstance(Context ctx){
        if(controller==null){
            controller=new Controller(ctx);
        }
        return controller;
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public void addToRequestQueue(Request request){
        getRequestQueue().add(request);
    }
}
