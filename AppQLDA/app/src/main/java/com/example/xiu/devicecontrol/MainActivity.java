package com.example.xiu.devicecontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2;
    String urlpostdata = "http://192.168.137.1:1234/QLDA/Updatestatus.php"; // remember change this address if change directory or ip address

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btnon);
        btn2 = (Button)findViewById(R.id.btnoff);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ON(urlpostdata);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OFF(urlpostdata);
            }
        });

    }

    public void ON (String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);   //Using volley library
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,   //Using Post method
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("1")) {           //After we'd Send from function params, if we received 1 ,we would toast ON
                            Toast.makeText(MainActivity.this, "-ON-", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Server is busy, try again!!!", Toast.LENGTH_SHORT).show();
                        //Log.d("AAA","Lỗi\n" + error.toString());
                    }
                }


        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("status","1");   //Send to table "status" , data 1
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    public void OFF (String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);   //Using volley library
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,   //Using Post method
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("0")) {   //After we'd Send from function params,we would toast OFF
                            Toast.makeText(MainActivity.this, "-OFF-", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Server is busy, try again!!!", Toast.LENGTH_SHORT).show();
                    }
                }


        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("status","0");   //Send to table "status" , data 0
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}


