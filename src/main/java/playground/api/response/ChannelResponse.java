package playground.api.response;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChannelResponse {

  private String id;
  private String name;
  private Date created;
  private Date updated;
}
