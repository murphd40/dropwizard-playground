package playground.dao.model;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Channel {

  private String id;
  private String name;
  private Date created;
  private Date updated;
}
