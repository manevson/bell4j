package io.github.stefanmanev.bell4j;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogTracker {

   private final Bell bell;
   private final TelegramBellConfiguration config;

   public LogTracker(Bell bell,
                     TelegramBellConfiguration config) {
      this.bell = bell;
      this.config = config;
   }

   Map<String, LogStats> logCounter = new ConcurrentHashMap<>();

   protected void trackLog(Integer threshold, Duration timeWindow, String msg, long timestamp) {
      StackTraceElement caller = Thread.currentThread().getStackTrace()[3];
      String logId = caller.getClassName() + ":" + caller.getMethodName() + ":" + caller.getLineNumber();

      LogStats logStats = logCounter.computeIfAbsent(logId, id -> new LogStats(logId, threshold, timeWindow, msg, config.getTimeBetweenAlerts()));
      try {
         logStats.recordOccurrence(timestamp);
      } catch (ThresholdExceededException e) {
         bell.ring(e.getMessage());
      }
   }
}
