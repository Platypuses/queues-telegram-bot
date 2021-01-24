package com.platypuses.bot.api.adapter;

import com.platypuses.bot.model.response.HelloWorldResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HelloWorldApiAdapter {

  @GET("api/v1/hello")
  Call<HelloWorldResponse> helloWorld();

}
