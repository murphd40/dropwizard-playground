package playground.dao.model;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {

  private String id;
  private String content;
  private String channelId;
  private Date created;
  private Date updated;
}
