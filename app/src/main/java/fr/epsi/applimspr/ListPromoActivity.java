package fr.epsi.applimspr;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



public class ListPromoActivity extends AppliActivity {

    ListView listView;
    ArrayList<Promotion> arrayList;
    SwipeRefreshLayout swipeRefresh;
    String token = MainActivity.token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listpromo_activity);
        showBackBtn();

        listView = findViewById(R.id.listView);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        arrayList = new ArrayList<>();
        new fetchData().execute();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new fetchData().execute();
            }
        });
    }


    private class fetchData extends AsyncTask<String,String,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeRefresh.setRefreshing(true);
        }

        @Override
        protected String doInBackground(String... params) {
            arrayList.clear();
            String result = null;
            try {
                URL url = new URL("http://10.0.2.2:8000/test");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput( true );
                conn.setInstanceFollowRedirects( false );
                conn.setRequestMethod( "POST" );
                conn.setRequestProperty( "Content-Type", "application/json");
                conn.setDoInput(true);
                conn.setRequestProperty( "charset", "utf-8");
                conn.setUseCaches(false);
                String jsonInputString = "{\"data\":[{\"id\":1,\"libelle\":\"promotion -10%\", \"date expiration\":\"11-11-2011\"},\n" +
                        "{\"id\":2,\"libelle\":\"promotion -20%\", \"date expiration\":\"12-12-2012\"}\n" +
                        "]}";
                System.out.println("token##########################"+token);
                //TODO : remplacer jsonInputString par token au foramt json
                try(OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
                conn.connect();

                    if(conn.getResponseCode()== HttpURLConnection.HTTP_OK){
                        InputStreamReader inputStream = new InputStreamReader(conn.getInputStream());
                        BufferedReader reader = new BufferedReader(inputStream);
                        StringBuilder stringBuilder = new StringBuilder();
                        String temp;
                        while((temp = reader.readLine())!=null){
                            stringBuilder.append(temp);
                        }
                        result = stringBuilder.toString();
                    }
                    else{
                        return "error!";
                    }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            swipeRefresh.setRefreshing(false);
            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("data");
                for(int i=0;i<array.length();i++){
                    JSONObject jsonObject = array.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String libelle = jsonObject.getString("libelle");
                    String dateExpiration = jsonObject.getString("date expiration");
                    Promotion promotion = new Promotion();
                    promotion.setId("Promo N°: "+id);
                    promotion.setLibelle(libelle);
                    promotion.setDateExpiration("date d'éxpiration: "+dateExpiration);
                    arrayList.add(promotion);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            PromotionAdapter adapter = new PromotionAdapter(ListPromoActivity.this,arrayList);
            listView.setAdapter(adapter);
        }
    }
}

