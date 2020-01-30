package fr.epsi.demo.model;

import org.json.JSONObject;

import java.io.Serializable;

public class Student implements Serializable {

    private String name="";
    private String email="";
    private String pictureUrl="";

    public Student(JSONObject jsonObject){
        name=jsonObject.optString("name","");
        email=jsonObject.optString("email","");
        pictureUrl=jsonObject.optString("picture_url","");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
