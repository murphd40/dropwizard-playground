package playground.api.response;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {

  private String id;
  private String content;
  private String channelId;
  private Date created;
  private Date updated;
}
