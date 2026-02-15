package io.github.manevson.bell4j;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogTracker {

   private static final int CALLER_STACK_INDEX = 4;

   private final Ringer ringer;
   private final BaseConfiguration config;

   public LogTracker(Ringer ringer,
                     BaseConfiguration config) {
      this.ringer = ringer;
      this.config = config;
   }

   Map<String, LogStats> logCounter = new ConcurrentHashMap<>();

   protected void trackLogWithExplicitThreshold(Integer threshold, Duration timeWindow, String msg, long timestamp) {
      String logId = generateLogId();
      LogStats logStats = logCounter.computeIfAbsent(logId, id -> new LogStats(logId, threshold, timeWindow, msg, config.getTimeBetweenAlerts(), config.getAlertHeader()));
      try {
         logStats.recordOccurrence(timestamp);
      } catch (ThresholdExceededException e) {
         ringer.ringBell(e.getMessage());
      }
   }

   protected void trackLogWithDefaultThreshold(String msg, long timestamp) {
      String logId = generateLogId();
      LogStats logStats = logCounter.computeIfAbsent(logId, id -> new LogStats(logId, config.getDefaultThreshold(), config.getDefaultTimeWindow(), msg, config.getTimeBetweenAlerts(), config.getAlertHeader()));
      try {
         logStats.recordOccurrence(timestamp);
      } catch (ThresholdExceededException e) {
         ringer.ringBell(e.getMessage());
      }
   }

   private String generateLogId() {
      StackTraceElement caller = Thread.currentThread().getStackTrace()[CALLER_STACK_INDEX];
      return caller.getClassName() + ":" + caller.getMethodName() + ":" + caller.getLineNumber();
   }
}
