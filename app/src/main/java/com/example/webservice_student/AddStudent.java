package com.example.webservice_student;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AddStudent extends AppCompatActivity {
    private EditText edtName, edtGamil, edtSdt;
    private Button btnAdd, btnHuy;
    private String urlString= "http://192.168.227.12/androidwebsite/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        initUI();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddStudent(urlString);
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initUI() {
        edtName     = findViewById(R.id.edtName);
        edtGamil    = findViewById(R.id.edtEmail);
        edtSdt      = findViewById(R.id.edtSdt);
        btnAdd      = findViewById(R.id.btnAdd);
        btnHuy      = findViewById(R.id.btnCancel);
    }

    private void AddStudent(String Url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("successfully")){
                    Toast.makeText(AddStudent.this, "Student add successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddStudent.this,MainActivity.class));
                }else{
                    Toast.makeText(AddStudent.this, "Error add student!", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddStudent.this, "Error", Toast.LENGTH_SHORT).show();
                        Log.d("AAA","Error!\n" + error.toString());
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("hoten",edtName.getText().toString().trim());
                params.put("Gmail",edtGamil.getText().toString().trim());
                params.put("Sdt",edtSdt.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}