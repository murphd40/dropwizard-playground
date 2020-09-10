package playground.api.request;

import lombok.Data;

@Data
public class CreateMessageRequest {

  private String channelId;
  private String content;
}
