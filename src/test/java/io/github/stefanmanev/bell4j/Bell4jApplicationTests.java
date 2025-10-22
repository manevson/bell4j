package io.github.stefanmanev.bell4j;

import org.junit.jupiter.api.Test;

import java.time.Duration;

class Bell4jApplicationTests {

	@Test
	void testRing() {
      TelegramBellConfiguration configuration = new TelegramBellConfiguration();
      configuration.setBotId("8264203145:AAFNniY4VsFQLfu8IwKwjd5PCrtVuErS0VM");
      configuration.setChatId("-4875525489");
		TelegramBell bell = new TelegramBell(configuration);
		try {
         bell.ring("Ring!");
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }
   }

   @Test
   void testRingFail() {
      TelegramBellConfiguration configuration = new TelegramBellConfiguration();
      configuration.setBotId("8264203145:1123123123asd");
      configuration.setChatId("-123123123123");
      TelegramBell bell = new TelegramBell(configuration);
      try {
         bell.ring("Ring!");
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }
   }

   @Test
   void testLog() {
      TelegramBellConfiguration configuration = new TelegramBellConfiguration();
      configuration.setBotId("8264203145:AAFNniY4VsFQLfu8IwKwjd5PCrtVuErS0VM");
      configuration.setChatId("-4875525489");
      configuration.setTimeBetweenAlerts(Duration.parse("PT1M"));
      TelegramBell telegramBell = new TelegramBell(configuration);
      LogTracker logTracker = new LogTracker(telegramBell, configuration);
      BaseBellLogger bell = new BaseBellLogger(logTracker);
      for (int i=0; i<=6; i++) {
         bell.info(3, Duration.ofMinutes(1), "Log occurred -- random arg: {}", "blabla");
      }
   }

}
