package com.example.antoinerousselot.apppartiel;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ZoomImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String imageURL=bundle.getString("image");
        String title=bundle.getString("title");
        setContentView(R.layout.activity_zoom_image);
        TextView tv= findViewById(R.id.textView);
        ImageView iv=findViewById(R.id.imageView);

        tv.setText(title);
        Picasso.get().load(imageURL).into(iv);
    }

}
