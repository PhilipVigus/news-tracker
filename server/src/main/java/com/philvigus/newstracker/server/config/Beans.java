package com.philvigus.newstracker.server.config;

import com.rometools.rome.io.SyndFeedInput;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {
  @Bean
  public SyndFeedInput syndFeedInput() {
    return new SyndFeedInput();
  }
}
