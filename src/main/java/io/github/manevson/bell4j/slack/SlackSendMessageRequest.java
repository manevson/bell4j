package io.github.manevson.bell4j.slack;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SlackSendMessageRequest {
   @JsonProperty("text")
   private String text;
}
