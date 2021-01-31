package com.example.loblawdigital_test;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.fragment.NavHostFragment;

import com.example.loblawdigital_test.CustomProductList;
import com.example.loblawdigital_test.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FirstFragment<Cntext> extends Fragment {
    private ListView listView;

    private ArrayList<String> productPrice=new ArrayList<String>();
    private ArrayList<String> productlNames=new ArrayList<String>();
    private ArrayList<String> imageUrls=new ArrayList<String>();
    // private String[] productPrice;;
    // private String[] productlNames;
    public JSONArray jsonArr = new JSONArray();



    JsonTask ttt = new JsonTask();
    String result;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ListView listView=(ListView)  view.findViewById(R.id.myList);
        //  listView.addHeaderView(textView);
        ttt.PasParam(getActivity(), view, getActivity());
        ttt.execute("https://gist.githubusercontent.com/r2vq/2ac197145db3f6cdf1a353feb744cf8e/raw/b1e722f608b00ddde138a0eef2261c6ffc8b08d7/cart.json");

        // For populating list data


  //    CustomProductList customProductList = new CustomProductList(this.getActivity(), productlNames.toArray(new String[0]), productPrice.toArray(new String[0]));
 //      listView.setAdapter(customProductList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                JSONObject jsonObj = null;

                for (int i = 0; i < jsonArr.length(); i++)
                {

                    try {
                        jsonObj = jsonArr.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        String productlName = jsonObj.getString("name");
                        String productPrice = jsonObj.getString("price");
                        String productID = jsonObj.getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


                try {
                    jsonObj = jsonArr.getJSONObject(position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                ((MainActivity)getActivity()).setProductModel(jsonObj);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);

            }
        });





    }


    private class JsonTask extends AsyncTask<String, String, String> {
        private Context mContext;
        private View rootView;
        private Activity activity;
        protected void onPreExecute() {
            super.onPreExecute();

            //     pd = new ProgressDialog(MainActivity.this);
            //   pd.setMessage("Please wait");
            //    pd.setCancelable(false);
            //    pd.show();
        }
        private void PasParam(Context context, View rootView, Activity activity){
            this.mContext=context;
            this.rootView=rootView;
            this.activity = activity;
        }
        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }



                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            JSONObject jsonObj = null;
            //   JSONArray jsonArr = new JSONArray();
            try {
                jsonObj = new JSONObject(result);
                jsonArr  = jsonObj.getJSONArray("entries");
            } catch (JSONException e) {
                e.printStackTrace();
            }



            for (int i = 0; i < jsonArr.length(); i++)
            {

                try {
                    jsonObj = jsonArr.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    productlNames.add(jsonObj.getString("name"));
                    productPrice.add(jsonObj.getString("price"));
                    imageUrls.add(jsonObj.getString("image"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
           CustomProductList customProductList = new CustomProductList(activity, productlNames.toArray(new String[0]), productPrice.toArray(new String[0]), imageUrls.toArray(new String[0]));
            ListView listView=(ListView)  rootView.findViewById(R.id.myList);
           listView.setAdapter(customProductList);



          //  TextView text = (TextView) rootView.findViewById(R.id.myTxt);
          //  text.setText(result);
        }
    }

}
