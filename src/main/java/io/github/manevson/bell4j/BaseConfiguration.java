package io.github.manevson.bell4j;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "bell4j.base")
@Data
public class BaseConfiguration {
   private Duration timeBetweenAlerts;
   private Duration defaultTimeWindow;
   private Integer defaultThreshold;
   private String alertHeader;
}
