package fr.epsi.demo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import fr.epsi.demo.model.Student;

public class StudentsActivity extends DemoActivity {

    private StudentAdapter adapter;
    //private String data=" {\"items\": [{\"picture_url\": \"https://www.numerama.com/content/uploads/2019/05/trou-noir-espace-univers-astronomie.jpg\",\"name\": \"Allan Arraud\",\"email\": \"allan.arraud@epsi.fr\"},{\"picture_url\": \"https://helpx.adobe.com/content/dam/help/en/stock/how-to/visual-reverse-image-search/jcr_content/main-pars/image/visual-reverse-image-search-v2_intro.jpg\",\"name\": \"Moussa Ba2\",\"email\": \"moussa.ba2@epsi.fr\"},{\"picture_url\": \"https://media.gettyimages.com/photos/colorful-powder-explosion-in-all-directions-in-a-nice-composition-picture-id890147976?s=2048x2048\",\"name\": \"Hajar Benaissa\",\"email\": \"hajar.benaissa@epsi.fr\"},{\"picture_url\": \"https://interactive-examples.mdn.mozilla.net/media/examples/grapefruit-slice-332-332.jpg\",\"name\": \"Lilian Berna\",\"email\": \"lilian.berna@epsi.fr\"},{\"picture_url\": \"https://cdn.futura-sciences.com/buildsv6/images/largeoriginal/2/3/1/2310c9171a_50157784_pia23441.jpg\",\"name\": \"Maxime Holec\",\"email\": \"maxime.holec@epsi.fr\"}]}";
    private ArrayList<Student> students;

    public static void display(DemoActivity activity){
        Intent intent=new Intent(activity, StudentsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_name);
        students=new ArrayList<>();
        showBackBtn();
        ListView listView=findViewById(R.id.listViewStudents);
        adapter=new StudentAdapter(this,R.layout.c_student,students);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentDetailsActivity.display(StudentsActivity.this,students.get(position));
            }
        });
        String urlStr="https://uc41515dae54ad4e1e0dfc525ab3.dl.dropboxusercontent.com/cd/0/get/AxFkS5nmUoGRVH76P-WKiQspZvk6xTax7jqP9lHKa4wRDC8Yrc7MvBgjaopgKXNEOWCMHfbiZaA_vW0lfGHTG7T3fA3oZoBa2rRliPE1iKLtpovPstTKlk-C3zzEJlRX5nQ/file?_download_id=2902123733814752900950036842253403825088084963068634499224612858925&_notify_domain=www.dropbox.com&dl=1";

//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                String urlStr="https://uc41515dae54ad4e1e0dfc525ab3.dl.dropboxusercontent.com/cd/0/get/AxFkS5nmUoGRVH76P-WKiQspZvk6xTax7jqP9lHKa4wRDC8Yrc7MvBgjaopgKXNEOWCMHfbiZaA_vW0lfGHTG7T3fA3oZoBa2rRliPE1iKLtpovPstTKlk-C3zzEJlRX5nQ/file?_download_id=2902123733814752900950036842253403825088084963068634499224612858925&_notify_domain=www.dropbox.com&dl=1";
//                final String result=ToolsWS.call(urlStr);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        initData(result);
//                    }
//                });
//            }
//        }.start();
//
//        new AsyncTask<Void,Void,String>(){
//            @Override
//            protected String doInBackground(Void... voids) {
//                String urlStr="https://uc41515dae54ad4e1e0dfc525ab3.dl.dropboxusercontent.com/cd/0/get/AxFkS5nmUoGRVH76P-WKiQspZvk6xTax7jqP9lHKa4wRDC8Yrc7MvBgjaopgKXNEOWCMHfbiZaA_vW0lfGHTG7T3fA3oZoBa2rRliPE1iKLtpovPstTKlk-C3zzEJlRX5nQ/file?_download_id=2902123733814752900950036842253403825088084963068634499224612858925&_notify_domain=www.dropbox.com&dl=1";
//                return ToolsWS.call(urlStr);
//            }
//
//            @Override
//            protected void onPostExecute(String o) {
//                super.onPostExecute(o);
//                initData(o);
//            }
//        }.execute();

        new HttpAsyTask(urlStr, new HttpAsyTask.HttpAsyTaskListener() {
            @Override
            public void webServiceDone(String result) {
                initData(result);
            }

            @Override
            public void webServiceError(Exception e) {
                displayToast(e.getMessage());
            }
        }).execute();
    }

    private void initData(String data) {
        try {
            JSONObject jsonObject;
            jsonObject=new JSONObject(data);
            JSONArray jsonArray=jsonObject.getJSONArray("items");
            for(int i=0;i<jsonArray.length();i++){
                Student student=new Student(jsonArray.getJSONObject(i));
                students.add(student);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        displayToast(String.valueOf(students.size()));
        adapter.notifyDataSetChanged();
    }
}
