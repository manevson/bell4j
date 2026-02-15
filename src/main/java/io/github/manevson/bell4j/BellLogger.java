package io.github.manevson.bell4j;

import java.time.Duration;

public interface BellLogger {
   void debug(Integer threshold, Duration timeWindow, String msg, Object... args);
   void info(Integer threshold, Duration timeWindow, String msg, Object... args);
   void warn(Integer threshold, Duration timeWindow, String msg, Object... args);
   void error(Integer threshold, Duration timeWindow, String msg, Object... args);

   void debug(String msg, Object... args);
   void info(String msg, Object... args);
   void warn(String msg, Object... args);
   void error(String msg, Object... args);
}
