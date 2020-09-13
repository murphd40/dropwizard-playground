package playground.api.resources;

import com.codahale.metrics.annotation.Timed;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import playground.api.response.Saying;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class PlaygroundResource {

  private final String template;
  private final String defaultName;
  private final AtomicLong counter;

  public PlaygroundResource(String template, String defaultName) {
    this.template = template;
    this.defaultName = defaultName;
    this.counter = new AtomicLong();
  }

  @GET
  @Timed
  public Saying sayHello(@QueryParam("name") Optional<String> name) {
    final String value = String.format(template, name.orElse(defaultName));
    return new Saying(counter.incrementAndGet(), value);
  }
}
