package com.platypuses.bot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@SpringBootConfiguration
@EnableConfigurationProperties(BotProperties.class)
public class AppConfig {

  private final BotProperties botProperties;

  @Autowired
  public AppConfig(BotProperties botProperties) {
    this.botProperties = botProperties;
  }

  @Bean
  public Retrofit retrofit() {
    return new Retrofit
        .Builder()
        .baseUrl(botProperties.getBackendBaseUrl())
        .addConverterFactory(JacksonConverterFactory.create())
        .build();
  }

}
