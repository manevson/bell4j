package io.github.manevson.bell4j;

import io.github.manevson.bell4j.slack.SlackBell;
import io.github.manevson.bell4j.slack.SlackBellConfiguration;
import io.github.manevson.bell4j.telegram.TelegramBell;
import io.github.manevson.bell4j.telegram.TelegramBellConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({BaseConfiguration.class, TelegramBellConfiguration.class, SlackBellConfiguration.class})
@ConditionalOnClass(name = "org.springframework.context.ConfigurableApplicationContext")
public class Bell4jAutoConfiguration {

   @Bean
   @ConditionalOnProperty(value = "bell4j.telegram.enabled", havingValue = "true")
   public Bell telegramBell(TelegramBellConfiguration configuration) {
      return new TelegramBell(configuration);
   }

   @Bean
   @ConditionalOnProperty(value = "bell4j.slack.enabled", havingValue = "true")
   public Bell slackBell(SlackBellConfiguration configuration) {
      return new SlackBell(configuration);
   }

   @Bean
   public Ringer ringer(ApplicationEventPublisher publisher) {
      return new Ringer(publisher);
   }

   @Bean
   public LogTracker logTracker(Ringer ringer, BaseConfiguration configuration) {
      return new LogTracker(ringer, configuration);
   }

   @Bean
   public BellLogger bellLogger(LogTracker logTracker) {
      return new BaseBellLogger(logTracker);
   }
}
