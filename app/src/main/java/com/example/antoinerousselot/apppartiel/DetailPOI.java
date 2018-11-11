package com.example.antoinerousselot.apppartiel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailPOI extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Bundle bundle = getIntent().getExtras();

        final String response=bundle.getString("initialResponse");
        Bundle bundle2=new Bundle();
        bundle2.putString("response",response.toString());
        Intent i= new Intent(DetailPOI.this,DisplayPOIActivity.class);
        i.putExtras(bundle2);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_poi);
        Bundle bundle = getIntent().getExtras();
        String response=bundle.getString("response");
        JSONObject myJson= null;
        JSONArray myElements=null;
        JSONArray myImages=null;
        JSONObject myObject=null;

        try {
            myJson = new JSONObject(response.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            myElements= myJson.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            myImages=myElements.getJSONObject(0).getJSONArray("medias");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*LinearLayout rl = findViewById(R.id.linearView);
        LinearLayout rl2=rl.findViewById(R.id.linearView2);
        LinearLayout rl3=rl.findViewById(R.id.linearView3);*/
        ImageView iv = (ImageView) findViewById(R.id.imageView2);
        ImageView iv2 = (ImageView) findViewById(R.id.imageView3);
        ImageView iv3 = (ImageView) findViewById(R.id.imageView4);
        List<ImageView> myImageViews= new ArrayList<>();
        myImageViews.add(iv);
        myImageViews.add(iv2);
        myImageViews.add(iv3);
        TextView title= findViewById(R.id.textView2);
        TextView description=findViewById(R.id.textView3);
        try {
            myObject=myElements.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Set title
        try {
            title.setText(myObject.getString("name"));
        } catch (JSONException e) {
            title.setText("Error");
            e.printStackTrace();
        }
        //Set Description
        try {
            description.setText(myObject.getString("description"));
        } catch (JSONException e) {
            description.setText("Error loading description");
            e.printStackTrace();
        }
        //Set images
        for(int i=0;i<3;i++){
            if (i<=myImages.length()-1){
                JSONObject myMedia=null;
                try {
                    myMedia=myImages.getJSONObject(i);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    Picasso.get().load(myMedia.getString("url")).into(myImageViews.get(i));
                    final JSONObject finalMyMedia = myMedia;
                    final JSONObject finalMyObject = myObject;
                    myImageViews.get(i).setOnClickListener(new View.OnClickListener() {
                        //add on click listener
                        @Override
                        public void onClick(View v) {
                            try {
                                actionOnClick(finalMyMedia.getString("url"), finalMyObject.getString("name"));
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(),"Unable to load resource",Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                Picasso.get().load("http://chittagongit.com//images/picture-unavailable-icon/picture-unavailable-icon-11.jpg").into(myImageViews.get(i));
                final JSONObject finalMyObject1 = myObject;
                myImageViews.get(i).setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        try {
                            actionOnClick("http://chittagongit.com//images/picture-unavailable-icon/picture-unavailable-icon-11.jpg", finalMyObject1.getString("name"));
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),"Unable to load resource",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                    });

            }
        }

    }


    protected void actionOnClick(String url, String title){

        Bundle bundle=new Bundle();
        bundle.putString("title",title);
        bundle.putString("image",url);
        Intent i= new Intent(DetailPOI.this,ZoomImageActivity.class);
        i.putExtras(bundle);

        startActivity(i);
    }

}
