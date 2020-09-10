package playground.api.response;

import lombok.Value;

@Value
public class Saying {
  private long id;
  private String content;
}
