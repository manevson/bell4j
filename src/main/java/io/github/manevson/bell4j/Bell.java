package io.github.manevson.bell4j;

public interface Bell {
   void ring(String message);
   void ring(RingEvent ringEvent);
}
