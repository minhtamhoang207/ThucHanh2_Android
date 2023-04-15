package com.example.th2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.th2.R;

public class SimpleImageArrayAdapter extends BaseAdapter {
    Context context;
    int[] images;
//    String[] names;
    LayoutInflater inflater;

    public SimpleImageArrayAdapter(Context applicationContext, int[] images) {
        this.context = applicationContext;
        this.images = images;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.image_spinner_item, null);
        ImageView icon = (ImageView) view.findViewById(R.id.spinner_image_view);
        icon.setImageResource(images[i]);
//        names.setText(countryNames[i]);
        return view;
    }
}
