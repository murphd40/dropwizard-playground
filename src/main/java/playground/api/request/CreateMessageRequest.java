package playground.api.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateMessageRequest {

  @NotBlank private String channelId;
  @NotBlank private String content;
}
