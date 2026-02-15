package io.github.manevson.bell4j.slack;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bell4j.slack")
@Data
public class SlackBellConfiguration {
   private String alertUrl;
}
