package io.github.manevson.bell4j.slack;

import io.github.manevson.bell4j.Bell;
import io.github.manevson.bell4j.RingEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

public class SlackBell implements Bell {

   private final SlackBellConfiguration configuration;
   private final HttpHeaders baseHeaders;
   private final RestTemplate restTemplate = new RestTemplate();

   public SlackBell(SlackBellConfiguration configuration) {
      this.configuration = configuration;
      this.baseHeaders = new HttpHeaders();
      baseHeaders.setContentType(MediaType.APPLICATION_JSON);
   }

   @Override
   @EventListener
   @Async
   public void ring(RingEvent ringEvent) {
      sendSlackMessage(ringEvent.getMessage());
   }

   @Override
   public void ring(String message) {
      sendSlackMessage(message);
   }

   private void sendSlackMessage(String message) {
      SlackSendMessageRequest request = new SlackSendMessageRequest(message);
      HttpEntity<SlackSendMessageRequest> requestEntity = new HttpEntity<>(request, baseHeaders);
      try {
         restTemplate.exchange(configuration.getAlertUrl(), HttpMethod.POST, requestEntity, String.class);
      } catch (Exception e) {
         String errorMsg = "Error ringing slack chat at " + configuration.getAlertUrl() + " with message: " +
                 e.getMessage();
         throw new RuntimeException(errorMsg, e);
      }
   }
}