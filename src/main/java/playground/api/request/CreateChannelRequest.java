package playground.api.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateChannelRequest {

  @NotBlank private String name;
}
