package com.platypuses.bot.exception;

public class TelegramBotException extends RuntimeException {

  private static final long serialVersionUID = 222165091946531576L;

  public TelegramBotException(String message) {
    super(message);
  }

}
