package io.github.manevson.bell4j;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TelegramBellConfiguration.class)
@ConditionalOnClass(name = "org.springframework.context.ConfigurableApplicationContext")
public class Bell4jTelegramAutoConfiguration {

   @Bean
   public LogTracker logTracker(Bell bell, TelegramBellConfiguration configuration) {
      return new LogTracker(bell, configuration);
   }

   @Bean
   public BellLogger bellLogger(LogTracker logTracker) {
      return new BaseBellLogger(logTracker);
   }

   @Bean
   public Bell bell(TelegramBellConfiguration configuration) {
      return new TelegramBell(configuration);
   }
}