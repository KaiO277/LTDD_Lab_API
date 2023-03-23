package com.example.webservice_student;

import static com.example.webservice_student.R.id.btnAddStudent;
import static kotlinx.coroutines.BuildersKt.withContext;
import static kotlinx.coroutines.CoroutineScopeKt.CoroutineScope;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Gson gson= new Gson();
    private StudentAdapter studentAdapter;
    private RecyclerView recyclerView;
    private Button btnAddStudent;
    
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentAdapter = new StudentAdapter();
        recyclerView = findViewById(R.id.rcvStudent);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData();
        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddStudent.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        StudentApi studentApi = RetrofitClient.create();
        Call<ResponseBody> call = studentApi.getStudent();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    List<Student> students = gson.fromJson(response.body().string(), new TypeToken<List<Student>>(){}.getType());
                    studentAdapter.setStudents(students);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}






