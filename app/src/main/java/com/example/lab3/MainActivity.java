package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private String urlJsonObj = "http://10.24.48.97/API-L2-NKW/Lab3/person_object.json";
    private String urlJsonArr = "http://10.24.48.97/API-L2-NKW/Lab3/array.json";
    private Button btn1,btn2;
    private TextView tv1;
    private String jsonResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        tv1 = findViewById(R.id.tv1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                makeJsonArray();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeJsonObject();
            }
        });
    }
    public void makeJsonObject(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,urlJsonObj, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                     try {
                         String name = response.getString("name");
                         String email = response.getString("email");
                         JSONObject phone = response.getJSONObject("phone");
                         String home = phone.getString("home");
                         String  mobile = phone.getString("mobile");

                         jsonResponse="";
                         jsonResponse+= "Name: "+name+"\n\n";
                         jsonResponse+= "Email: "+email+"\n\n";
                         jsonResponse+= "Home: "+home+"\n\n";
                         jsonResponse+= "Mobile: "+mobile+"\n\n";

                         tv1.setText(jsonResponse);


                     }catch (JSONException e){
                         e.printStackTrace();
                     }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Loi",error.getMessage());
                    }
                });
        AppController.getInstance().addTorequestQueue(jsonObjectRequest);
    }
//    public void makeJsonArray(){
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlJsonArr,
//                new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for(int i=0;i<response.length();i++);{
//                    JSONObject object = (JSONObject)response.get(int);
//                    try {
//                        String name = object.getString("name");
//                        String email = object.getString("email");
//                        JSONObject phone = object.getJSONObject("phone");
//                        String home = phone.getString("home");
//                        String  mobile = phone.getString("mobile");
//
//                        jsonResponse="";
//                        jsonResponse+= "Name: "+name+"\n\n";
//                        jsonResponse+= "Email: "+email+"\n\n";
//                        jsonResponse+= "Home: "+home+"\n\n";
//                        jsonResponse+= "Mobile: "+mobile+"\n\n";
//
//                        tv1.setText(jsonResponse);
//
//
//                    }catch (JSONException e){
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("Loi",error.getMessage());
//            }
//        }
//        );
//    }

}