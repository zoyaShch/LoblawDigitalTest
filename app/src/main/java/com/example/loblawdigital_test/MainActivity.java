package com.example.loblawdigital_test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
public ProductModel pm = new ProductModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void setProductModel(JSONObject jsonObj) {
        try {
            pm.setName(jsonObj.getString("name"));
         //   pm.setID(jsonObj.getString("id"));
            pm.setImage(jsonObj.getString("image"));
            pm.setPrice(jsonObj.getString("price"));
            pm.setType(jsonObj.getString("type"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public ProductModel getProductModel() {
         return pm;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}