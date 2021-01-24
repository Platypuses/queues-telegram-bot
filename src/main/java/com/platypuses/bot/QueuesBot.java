package com.platypuses.bot;

import com.platypuses.bot.config.BotProperties;
import com.platypuses.bot.exception.TelegramBotException;
import com.platypuses.bot.handler.ExceptionHandler;
import com.platypuses.bot.handler.TelegramUpdateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class QueuesBot extends TelegramLongPollingBot {

  private final BotProperties botProperties;
  private final TelegramUpdateHandler telegramUpdateHandler;
  private final ExceptionHandler exceptionHandler;

  @Autowired
  public QueuesBot(BotProperties botProperties,
      TelegramUpdateHandler telegramUpdateHandler, ExceptionHandler exceptionHandler) {
    this.botProperties = botProperties;
    this.telegramUpdateHandler = telegramUpdateHandler;
    this.exceptionHandler = exceptionHandler;

    log.info("Bot initialized successfully!");
  }

  @Override
  public void onUpdateReceived(Update update) {
    try {
      telegramUpdateHandler.handleTelegramUpdate(this, update);
    } catch (TelegramBotException exception) {
      exceptionHandler.handleTelegramBotException(exception, this, update);
    } catch (Exception exception) {
      exceptionHandler.handleGenericBotException(exception, this, update);
    }
  }

  @Override
  public String getBotUsername() {
    return botProperties.getUsername();
  }

  @Override
  public String getBotToken() {
    return botProperties.getToken();
  }

}
