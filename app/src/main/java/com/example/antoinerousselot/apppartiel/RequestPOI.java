package com.example.antoinerousselot.apppartiel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RequestPOI extends AppCompatActivity {
    private String TAG="REQUESTPOI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String myId=bundle.getString("myId");
        setContentView(R.layout.activity_request_poi);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://voyage2.corellis.eu/api/v2/poi?id=" + myId;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG,response.toString());
                        Bundle bundle2 =new Bundle();
                        bundle2.putString("response",response.toString());

                        Intent monIntent= new Intent(RequestPOI.this,DetailPOI.class);
                        monIntent.putExtras(bundle2);
                        startActivity(monIntent);
                    }

                } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });
        queue.add(stringRequest);
    }

}
