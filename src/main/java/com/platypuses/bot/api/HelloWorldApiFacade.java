package com.platypuses.bot.api;

import com.platypuses.bot.model.response.HelloWorldResponse;
import retrofit2.Response;

public interface HelloWorldApiFacade {

  Response<HelloWorldResponse> helloWorld();

}
