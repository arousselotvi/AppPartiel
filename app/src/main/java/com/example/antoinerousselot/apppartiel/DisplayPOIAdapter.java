package com.example.antoinerousselot.apppartiel;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DisplayPOIAdapter extends BaseAdapter {
    List<Destination> biblio;
    private static final int itemViewPaire = 0;
    private static final int itemViewImpaire = 1;

    LayoutInflater inflater;

    private class ViewHolder {
        TextView tvType;
        TextView tvTitle;
        TextView tvDistance;
        ImageView iv;
    }

    public DisplayPOIAdapter(Context context, List<Destination> objects) {
        inflater = LayoutInflater.from(context);
        this.biblio = objects;
    }


    @Override
    public View getView(int position , View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView==null){
            Log.v("test","convertView is Null");
            holder=new ViewHolder();
            if (getItemViewType(position)==itemViewImpaire){
                convertView = inflater.inflate(R.layout.layoutitem, null);
            }
            else {
                convertView = inflater.inflate(R.layout.layoutitem2, null);
            }
            holder.tvType = (TextView) convertView.findViewById(R.id.textView2);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.textView3);
            holder.tvDistance = (TextView) convertView.findViewById(R.id.textView4);
            holder.iv = (ImageView) convertView.findViewById(R.id.imageView2);
            convertView.setTag(holder);
        } else {
            Log.v("test", "convertView is not null");
            holder=(ViewHolder) convertView.getTag();
        }


        Destination destination = biblio.get(position);
        holder.tvType.setText(destination.getType());
        holder.tvTitle.setText(destination.getTitle());
        String myDistance = String.format("%.2f", destination.getDistance());
        holder.tvDistance.setText(myDistance.toString()+" km");
        Picasso.get().load(destination.getImageURL()).into(holder.iv);

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return itemViewPaire;
        }
        else return itemViewImpaire;
    }
    @Override
    public int getCount() { return biblio.size();
    }

    @Override
    public Destination getItem(int position) { return biblio.get(position);
    }
    @Override
    public long getItemId(int position) { return position;
    }
}
