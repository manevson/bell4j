package io.github.stefanmanev.bell4j;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

@Data
public class LogStats {
   private String logId;
   private long timeWindow;
   private String readableTimeWindow;
   private Integer threshold;
   private Deque<Long> occurrenceTimestamps = new ConcurrentLinkedDeque<>();
   private String message;
   private long timeBetweenAlerts;
   private long lastSentAlert = 0L;

   public LogStats(String logId, Integer threshold, Duration timeWindow, String msg, Duration timeBetweenAlerts) {
      this.logId = logId;
      this.timeWindow = timeWindow.toMillis();
      this.readableTimeWindow = formatDuration(timeWindow);
      this.threshold = threshold;
      this.message = msg;
      this.timeBetweenAlerts = timeBetweenAlerts.toMillis();
   }

   public void recordOccurrence(long timestamp) throws ThresholdExceededException {
      occurrenceTimestamps.add(timestamp);

      while (!CollectionUtils.isEmpty(occurrenceTimestamps) && timestamp - occurrenceTimestamps.peekFirst() > timeWindow) {
         occurrenceTimestamps.pollFirst();
      }

      if (occurrenceTimestamps.size() >= threshold && timestamp - lastSentAlert > timeBetweenAlerts) {
         this.lastSentAlert = System.currentTimeMillis();
         String thresholdExceededMsg = "RING-A-DING:\n" + "Threshold (" + threshold + " occurrences within " + readableTimeWindow + ")" + " exceeded for log:\n" +
                 message;
         throw new ThresholdExceededException(thresholdExceededMsg);
      }
   }

   private String formatDuration(Duration duration) {
      String isoString = duration.toString();
      // Remove "PT" prefix and replace unit suffixes for better readability
      String readableString = isoString.substring(2)
              .replace("H", " hours ")
              .replace("M", " minutes ")
              .replace("S", " seconds ");
      return readableString.trim(); // Remove trailing space if no seconds
   }
}
