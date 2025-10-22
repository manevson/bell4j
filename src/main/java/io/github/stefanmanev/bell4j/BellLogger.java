package io.github.stefanmanev.bell4j;

import java.time.Duration;

public interface BellLogger {
   void debug(Integer threshold, Duration timeWindow, String msg, Object... args);
   void info(Integer threshold, Duration timeWindow, String msg, Object... args);
   void warn(Integer threshold, Duration timeWindow, String msg, Object... args);
   void error(Integer threshold, Duration timeWindow, String msg, Object... args);
}
