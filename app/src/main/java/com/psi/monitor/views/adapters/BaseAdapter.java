package com.psi.monitor.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by hamdhanywijaya@gmail.com on 8/10/17.
 */

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    Context context = null;
    LayoutInflater inflater = null;
    private List<T> data;

    BaseAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get((position < getCount()) ? position : (getCount() - 1));
    }

    public void removeData(int position) {
        data.remove(position);
    }

    void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    void loadImage(String url, int placeHolder, ImageView target) {
        Glide.with(context).load(url).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(placeHolder)
                .error(placeHolder)
                .into(target);

    }

    String distanceToString(float distance) {
        DecimalFormat df = new DecimalFormat("0.#");
        if (distance >= 1100) {
            return df.format(distance / 1000) + " km";
        } else {
            return df.format(distance) + " m";
        }
    }

    void distanceToTextView(float distance, TextView txtDistance, TextView txtUnit){
        DecimalFormat df = new DecimalFormat("0.#");
        if (distance >= 1100) {
            txtDistance.setText(df.format(distance / 1000));
            txtUnit.setText(" km");
        } else {
            txtDistance.setText(df.format(distance));
            txtUnit.setText(" m");
        }
    }
}
