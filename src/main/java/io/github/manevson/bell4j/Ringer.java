package io.github.manevson.bell4j;

import org.springframework.context.ApplicationEventPublisher;

public class Ringer {

   private final ApplicationEventPublisher publisher;

   public Ringer(ApplicationEventPublisher publisher) {
      this.publisher = publisher;
   }

   public void ringBell(String message) {
      RingEvent event = new RingEvent(message);
      publisher.publishEvent(event);
   }
}
