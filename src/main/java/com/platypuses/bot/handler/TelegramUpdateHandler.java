package com.platypuses.bot.handler;

import com.platypuses.bot.controller.HelloWorldController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
@Component
public class TelegramUpdateHandler {

  private static final String START_COMMAND = "/start";

  private final HelloWorldController helloWorldController;

  @Autowired
  public TelegramUpdateHandler(HelloWorldController helloWorldController) {
    this.helloWorldController = helloWorldController;
  }

  public void handleTelegramUpdate(AbsSender sender, Update update) {
    if (!update.hasMessage() || !update.getMessage().hasText()) {
      return;
    }

    Message message = update.getMessage();
    String messageText = message.getText().toLowerCase();

    switch (messageText) {
      case START_COMMAND:
        helloWorldController.helloWorld(sender, message.getChatId().toString());
        break;
      default:
        log.error("Can't handle message {}", messageText);
        break;
    }
  }

}
