package io.github.manevson.bell4j;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class BaseBellLogger implements BellLogger {

   private final LogTracker logTracker;

   public BaseBellLogger(LogTracker logTracker) {
      this.logTracker = logTracker;
   }

   @Override
   public void debug(Integer threshold, Duration timeWindow, String msg, Object... args) {
      log.debug(msg, args);
      logTracker.trackLog(threshold, timeWindow, msg, System.currentTimeMillis());
   }

   @Override
   public void info(Integer threshold, Duration timeWindow, String msg, Object... args) {
      log.info(msg, args);
      logTracker.trackLog(threshold, timeWindow, msg, System.currentTimeMillis());
   }

   @Override
   public void warn(Integer threshold, Duration timeWindow, String msg, Object... args) {
      log.warn(msg, args);
      logTracker.trackLog(threshold, timeWindow, msg, System.currentTimeMillis());
   }

   @Override
   public void error(Integer threshold, Duration timeWindow, String msg, Object... args) {
      log.error(msg, args);
      logTracker.trackLog(threshold, timeWindow, msg, System.currentTimeMillis());
   }
}
