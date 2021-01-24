package com.platypuses.bot.controller;

import com.platypuses.bot.service.HelloWorldService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
@Component
public class HelloWorldController {

  private final HelloWorldService helloWorldService;

  @Autowired
  public HelloWorldController(HelloWorldService helloWorldService) {
    this.helloWorldService = helloWorldService;
  }

  @SneakyThrows
  public void helloWorld(AbsSender sender, String chatId) {
    try {
      String result = helloWorldService.helloWorld();

      sender.execute(
          SendMessage
              .builder()
              .chatId(chatId)
              .text(result)
              .build()
      );

      log.info("helloWorld() success");
    } catch (Exception exception) {
      log.error("helloWorld() error: {}", exception.getMessage());
      throw exception;
    }
  }

}
