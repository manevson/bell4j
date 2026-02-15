package io.github.manevson.bell4j;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TelegramSendMessageRequest {
   @JsonProperty("chat_id")
   private String chatId;
   private String text;
}
