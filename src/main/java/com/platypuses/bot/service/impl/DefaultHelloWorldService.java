package com.platypuses.bot.service.impl;

import com.platypuses.bot.api.HelloWorldApiFacade;
import com.platypuses.bot.exception.TelegramBotException;
import com.platypuses.bot.model.response.HelloWorldResponse;
import com.platypuses.bot.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import javax.ws.rs.core.Response.Status;

@Service
public class DefaultHelloWorldService implements HelloWorldService {

  private static final String BACKEND_CALL_ERROR = "Server is not available. Try again.";

  private final HelloWorldApiFacade helloWorldApiFacade;

  @Autowired
  public DefaultHelloWorldService(HelloWorldApiFacade helloWorldApiFacade) {
    this.helloWorldApiFacade = helloWorldApiFacade;
  }

  @Override
  public String helloWorld() {
    Response<HelloWorldResponse> response = helloWorldApiFacade.helloWorld();

    if (response.code() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
      throw new TelegramBotException(BACKEND_CALL_ERROR);
    }

    if (response.body() == null) {
      return "";
    }

    return response.body().getMessage();
  }

}
