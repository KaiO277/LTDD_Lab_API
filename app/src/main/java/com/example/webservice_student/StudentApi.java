package com.example.webservice_student;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StudentApi {
    @GET("display.php")
    Call<ResponseBody> getStudent();
}