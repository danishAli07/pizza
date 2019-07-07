package com.synuptest.api;


import com.synuptest.model.PizzaModel;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {


    @GET("bins/19u0sf")
    Call<PizzaModel> callApi(
    );
}
