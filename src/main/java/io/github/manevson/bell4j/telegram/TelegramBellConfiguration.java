package io.github.manevson.bell4j.telegram;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "bell4j.telegram")
@Data
public class TelegramBellConfiguration {
   private String botId;
   private String chatId;
}
