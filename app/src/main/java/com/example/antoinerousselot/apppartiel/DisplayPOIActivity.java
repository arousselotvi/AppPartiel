package com.example.antoinerousselot.apppartiel;

import android.app.ListActivity;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DisplayPOIActivity extends ListActivity {
    List<Destination> maBibliotheque = new ArrayList<Destination>();
    private String TAG="DisplayPOI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle bundle = getIntent().getExtras();
        String response=bundle.getString("response");
        Log.i(TAG,response.toString());
        try {
            JSONObject myJson= new JSONObject(response.toString());
            JSONArray myPlaces= myJson.getJSONArray("data");
            Log.i(TAG,myPlaces.toString());
            ListView myListView= getListView();
            this.remplirBibliotheque(myPlaces);
            Log.i(TAG,maBibliotheque.toString());
            DisplayPOIAdapter adapter=new DisplayPOIAdapter(this, maBibliotheque);
            myListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            Log.i(TAG, "CECI NE DEVRAIT PAS ARRIVER");
            e.printStackTrace();
        }
    }

    private void remplirBibliotheque(JSONArray myPlaces){
        maBibliotheque.clear();
        try{
            for (int i=0; i<myPlaces.length(); i++){
                String myImageUrl= "";
                if(myPlaces.getJSONObject(i).getString("type")=="POI"){
                    if (myPlaces.getJSONObject(i).getString("media").length()!=0 && myPlaces.getJSONObject(i).getString("media")!= null) {
                        myImageUrl= myPlaces.getJSONObject(i).getString("media");
                    }
                    else {
                        myImageUrl = "https://static.thenounproject.com/png/628760-200.png";
                    }
                    maBibliotheque.add(new Destination(myPlaces.getJSONObject(i).getString("type"),myPlaces.getJSONObject(i).getString("display"),
                            myPlaces.getJSONObject(i).getString("distance"), myImageUrl));
                }
                else if(myPlaces.getJSONObject(i).getString("type")=="RESTAURANT"){
                    if (myPlaces.getJSONObject(i).getString("media").length()!=0 && myPlaces.getJSONObject(i).getString("media")!= null) {
                        myImageUrl= myPlaces.getJSONObject(i).getString("media");
                    }
                    else {
                        myImageUrl = "https://static.thenounproject.com/png/628760-200.png";
                    }
                    maBibliotheque.add(new Destination(myPlaces.getJSONObject(i).getString("type"),myPlaces.getJSONObject(i).getString("display"),
                            myPlaces.getJSONObject(i).getString("distance"), myImageUrl));
                }
                else if(myPlaces.getJSONObject(i).getString("type")=="HOTEL"){
                    if (myPlaces.getJSONObject(i).getString("media").length()!=0 && myPlaces.getJSONObject(i).getString("media")!= null) {
                        myImageUrl= myPlaces.getJSONObject(i).getString("media");
                    }
                    else {
                        myImageUrl = "https://static.thenounproject.com/png/628760-200.png";
                    }
                    maBibliotheque.add(new Destination(myPlaces.getJSONObject(i).getString("type"),myPlaces.getJSONObject(i).getString("display"),
                            myPlaces.getJSONObject(i).getString("distance"), myImageUrl));
                }
                else if(myPlaces.getJSONObject(i).getString("type")=="CITY"){
                    if (myPlaces.getJSONObject(i).getString("media").length()!=0 && myPlaces.getJSONObject(i).getString("media")!= null) {
                        myImageUrl= myPlaces.getJSONObject(i).getString("media");
                    }
                    else {
                        myImageUrl = "https://static.thenounproject.com/png/628760-200.png";
                    }
                    maBibliotheque.add(new Destination(myPlaces.getJSONObject(i).getString("type"),myPlaces.getJSONObject(i).getString("display"),
                            myPlaces.getJSONObject(i).getString("distance"), myImageUrl));
                }
            }
        }catch(JSONException e){
            Log.i(TAG, "CECI NE DEVRAIT PAS ARRIVER");
            e.printStackTrace();
            }
        }


    }


