package com.platypuses.bot.api.impl;

import com.platypuses.bot.api.HelloWorldApiFacade;
import com.platypuses.bot.api.adapter.HelloWorldApiAdapter;
import com.platypuses.bot.model.response.HelloWorldResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Response;
import retrofit2.Retrofit;

@Slf4j
@Component
public class HelloWorldApi implements HelloWorldApiFacade {

  private final HelloWorldApiAdapter helloWorldApiAdapter;

  @Autowired
  public HelloWorldApi(Retrofit retrofit) {
    this.helloWorldApiAdapter = retrofit.create(HelloWorldApiAdapter.class);
  }

  @Override
  @SneakyThrows
  public Response<HelloWorldResponse> helloWorld() {
    return helloWorldApiAdapter
        .helloWorld()
        .execute();
  }

}
