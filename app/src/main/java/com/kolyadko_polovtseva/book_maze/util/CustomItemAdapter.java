package com.kolyadko_polovtseva.book_maze.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.kolyadko_polovtseva.book_maze.R;
import com.kolyadko_polovtseva.book_maze.entity.DownloadPictureTaskBean;
import com.kolyadko_polovtseva.book_maze.row_model.RowModel;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;


public class CustomItemAdapter extends ArrayAdapter {
    private ArrayList<RowModel> modelItems = null;
    private Context context;
    private HashMap<Integer, ImageView> imageViews;


    public CustomItemAdapter(Context context, ArrayList<RowModel> resource) {
        super(context, R.layout.row, resource);
        this.context = context;
        this.modelItems = resource;
        imageViews = new HashMap<>();
    }

    public void updateData(ArrayList<RowModel> results) {
        modelItems.addAll(results);
        //Triggers the list update
        notifyDataSetChanged();
    }

    public ArrayList<RowModel> getModelItems() {
        return modelItems;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.textView);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        imageViews.put(position, imageView);

        name.setText(modelItems.get(position).getName());

        DownloadPictureTask downloadPictureTask = new DownloadPictureTask();
        String url = CloudinaryUtil.getInstance().generateImageUrl( modelItems.get(position).getImageUrl(), 100, 100);
        downloadPictureTask.execute(new DownloadPictureTaskBean(position, url));

        return convertView;
    }

    @Override
    public int getCount() {
        return modelItems.size();
    }

    class DownloadPictureTask extends AsyncTask<DownloadPictureTaskBean, Void, Bitmap> {
        private Integer id;

        @Override
        protected Bitmap doInBackground(DownloadPictureTaskBean... params) {
            try {
                this.id = params[0].getImageViewId();
                java.net.URL url = new java.net.URL(params[0].getImageUrl());
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView = imageViews.get(id);
            imageView.setImageBitmap(bitmap);
        }
    }
}
