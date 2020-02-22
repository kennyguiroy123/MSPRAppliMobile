package fr.epsi.applimspr;

import android.os.AsyncTask;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class WebService extends AsyncTask<Void,Void,Object>{

        interface HttpAsyTaskListener{
            void webServiceDone(String result);
            void webServiceError(Exception e);
        }

        private HttpAsyTaskListener httpAsyTaskListener;
        private String urlStr;
        private String password;
        private String login;

    public WebService(String url,String login,String password,HttpAsyTaskListener listener){
        httpAsyTaskListener=listener;
        urlStr=url;
        this.password = password;
        this.login = login;
/*        String urlParameters  = "Fname=Ram&Lname=Ramesh&Msisdn=6785459898";
        byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
        int postDataLength = postData.length;*/
        String request = "http://10.0.2.2:8000/test/";
    }

        @Override
        protected Object doInBackground(Void... voids) {
        return call(urlStr,login,password);
    }

        @Override
        protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(o instanceof Exception){
            httpAsyTaskListener.webServiceError((Exception)o);
        }
        else
            httpAsyTaskListener.webServiceDone(o.toString());
    }


        public Object call(String urlStr,String login,String pwd) {
        try {
            java.net.URL url = null;
            url = new URL(urlStr);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/json");
            conn.setRequestProperty("login",login);
            conn.setRequestProperty("password",pwd);
            conn.setRequestProperty( "charset", "utf-8");
            conn.setUseCaches(false);
            //conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            try {
                conn.connect();
                InputStream in = new BufferedInputStream(conn.getInputStream());
                return convertStreamToString(in);
            } finally {
                conn.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e;
        } catch (IOException e) {
            e.printStackTrace();
            return e;
        }
    }

        private String convertStreamToString(InputStream is){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            StringBuffer stringBuffer = new StringBuffer("");
            String line;

            String NL = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line + NL);
            }
            bufferedReader.close();
            return stringBuffer.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    }