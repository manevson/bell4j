package io.github.manevson.bell4j.telegram;

import io.github.manevson.bell4j.Bell;
import io.github.manevson.bell4j.RingEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

public class TelegramBell implements Bell {

   private static final String TELEGRAM_BOT_API_URL = "https://api.telegram.org/bot";

   private final String botId;
   private final String chatId;
   private final String notifyUrl;
   private final HttpHeaders baseHeaders;
   private final RestTemplate restTemplate = new RestTemplate();

   public TelegramBell(TelegramBellConfiguration config) {
      this.botId = config.getBotId();
      this.chatId = config.getChatId();
      this.notifyUrl = TELEGRAM_BOT_API_URL
              + this.botId
              + "/sendMessage";
      this.baseHeaders = new HttpHeaders();
      baseHeaders.setContentType(MediaType.APPLICATION_JSON);
   }

   private void sendMessageToTelegramChat(String message) {
      TelegramSendMessageRequest request = new TelegramSendMessageRequest(chatId, message);
      HttpEntity<TelegramSendMessageRequest> requestEntity = new HttpEntity<>(request, baseHeaders);
      try {
         restTemplate.exchange(notifyUrl, HttpMethod.POST, requestEntity, String.class);
      } catch (Exception e) {
         String errorMsg = "Error ringing telegram chat " + chatId + " via bot " + botId + " with message: " +
                 e.getMessage();
         throw new RuntimeException(errorMsg, e);
      }
   }

   public void ring(String message) {
      sendMessageToTelegramChat(message);
   }

   @Override
   @EventListener
   @Async
   public void ring(RingEvent ringEvent) {
      sendMessageToTelegramChat(ringEvent.getMessage());
   }
}
