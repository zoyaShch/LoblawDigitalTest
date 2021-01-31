package com.example.loblawdigital_test;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class CustomProductList extends ArrayAdapter {
    private String[] ProductNames;
    private String[] ProductPrice;
    private String[] imageid;
    private Activity activity;

    public CustomProductList(Activity activity, String[] productNames, String[] productPrice,String[] imageid) {
        super(activity, R.layout.row_item, productNames);
        this.activity = activity;
        this.ProductNames = productNames;
        this.ProductPrice = productPrice;
        this.imageid = imageid;
      //  this.imageid = imageid;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = activity.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row_item, null, true);
        TextView textViewProduct = (TextView) row.findViewById(R.id.textViewProduct);
        TextView textViewPrice = (TextView) row.findViewById(R.id.textViewPrice);
        ImageView imageProduct = (ImageView) row.findViewById(R.id.imageProduct);

        textViewProduct.setText(ProductNames[position]);
        textViewPrice.setText(ProductPrice[position]);
        new DownloadImageTask((ImageView) row.findViewById(R.id.imageProduct))
                .execute(imageid[position]);

        return  row;
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {

                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
