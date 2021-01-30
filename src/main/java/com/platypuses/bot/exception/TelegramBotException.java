package com.platypuses.bot.exception;

public class TelegramBotException extends RuntimeException {

  private static final long serialVersionUID = 22216509194653157L;

  public TelegramBotException(String message) {
    super(message);
  }

}
