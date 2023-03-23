package com.example.webservice_student;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StudentApi {
    @GET("display.php")
    Call<ResponseBody> getStudent();

    @POST("addStudent")
    Call<Void> addStudent(@Body Student student);
}