package com.platypuses.bot.handler;

import com.platypuses.bot.exception.TelegramBotException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class ExceptionHandler {

  private static final String INTERNAL_BOT_ERROR_MESSAGE = "Internal bot error. Try again later.";

  public void handleTelegramBotException(TelegramBotException exception, AbsSender sender, Update update) {
    sendExceptionMessage(sender, update, exception.getMessage());
  }

  public void handleGenericBotException(Exception exception, AbsSender sender, Update update) {
    log.error("Internal bot error", exception);
    sendExceptionMessage(sender, update, INTERNAL_BOT_ERROR_MESSAGE);
  }

  private void sendExceptionMessage(AbsSender sender, Update update, String message) {
    if (!update.hasMessage()) {
      return;
    }

    try {
      sender.execute(
          SendMessage
              .builder()
              .chatId(update.getMessage().getChatId().toString())
              .text(message)
              .build()
      );
    } catch (TelegramApiException telegramApiException) {
      log.error("Telegram API exception", telegramApiException);
    }
  }

}
