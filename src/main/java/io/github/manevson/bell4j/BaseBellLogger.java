package io.github.manevson.bell4j;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

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
      try {
         String formattedMessage = MessageFormatter.arrayFormat(msg, args).getMessage();
         logTracker.trackLogWithExplicitThreshold(threshold, timeWindow, formattedMessage, System.currentTimeMillis());
      } catch (Exception e) {
         log.error("Error during LogTracker#trackLog: ", e.getMessage(), e);
      }
   }

   @Override
   public void info(Integer threshold, Duration timeWindow, String msg, Object... args) {
      log.info(msg, args);
      try {
         String formattedMessage = MessageFormatter.arrayFormat(msg, args).getMessage();
         logTracker.trackLogWithExplicitThreshold(threshold, timeWindow, formattedMessage, System.currentTimeMillis());
      } catch (Exception e) {
         log.error("Error during LogTracker#trackLog: ", e.getMessage(), e);
      }
   }

   @Override
   public void warn(Integer threshold, Duration timeWindow, String msg, Object... args) {
      log.warn(msg, args);
      try {
         String formattedMessage = MessageFormatter.arrayFormat(msg, args).getMessage();
         logTracker.trackLogWithExplicitThreshold(threshold, timeWindow, formattedMessage, System.currentTimeMillis());
      } catch (Exception e) {
         log.error("Error during LogTracker#trackLog: ", e.getMessage(), e);
      }
   }

   @Override
   public void error(Integer threshold, Duration timeWindow, String msg, Object... args) {
      log.error(msg, args);
      try {
         String formattedMessage = MessageFormatter.arrayFormat(msg, args).getMessage();
         logTracker.trackLogWithExplicitThreshold(threshold, timeWindow, formattedMessage, System.currentTimeMillis());
      } catch (Exception e) {
         log.error("Error during LogTracker#trackLog: ", e.getMessage(), e);
      }
   }

   @Override
   public void debug(String msg, Object... args) {
      log.debug(msg, args);
      try {
         String formattedMessage = MessageFormatter.arrayFormat(msg, args).getMessage();
         logTracker.trackLogWithDefaultThreshold(formattedMessage, System.currentTimeMillis());
      } catch (Exception e) {
         log.error("Error during LogTracker#trackLog: ", e.getMessage(), e);
      }
   }

   @Override
   public void info(String msg, Object... args) {
      log.info(msg, args);
      try {
         String formattedMessage = MessageFormatter.arrayFormat(msg, args).getMessage();
         logTracker.trackLogWithDefaultThreshold(formattedMessage, System.currentTimeMillis());
      } catch (Exception e) {
         log.error("Error during LogTracker#trackLog: ", e.getMessage(), e);
      }
   }

   @Override
   public void warn(String msg, Object... args) {
      log.warn(msg, args);
      try {
         String formattedMessage = MessageFormatter.arrayFormat(msg, args).getMessage();
         logTracker.trackLogWithDefaultThreshold(formattedMessage, System.currentTimeMillis());
      } catch (Exception e) {
         log.error("Error during LogTracker#trackLog: ", e.getMessage(), e);
      }
   }

   @Override
   public void error(String msg, Object... args) {
      log.error(msg, args);
      try {
         String formattedMessage = MessageFormatter.arrayFormat(msg, args).getMessage();
         logTracker.trackLogWithDefaultThreshold(formattedMessage, System.currentTimeMillis());
      } catch (Exception e) {
         log.error("Error during LogTracker#trackLog: ", e.getMessage(), e);
      }
   }
}
