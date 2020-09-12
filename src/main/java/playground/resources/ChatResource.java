package playground.resources;

import com.google.inject.Inject;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import playground.api.request.CreateChannelRequest;
import playground.api.request.CreateMessageRequest;
import playground.api.response.ChannelResponse;
import playground.api.response.MessageResponse;
import playground.service.DbService;

@Path("/chat")
@Produces(MediaType.APPLICATION_JSON)
public class ChatResource {

  private final DbService dbService;

  @Inject
  public ChatResource(DbService dbService) {
    this.dbService = dbService;
  }

  @POST
  @Path("/message/create")
  public MessageResponse createMessage(CreateMessageRequest createMessageRequest) {
    return dbService.createMessage(createMessageRequest);
  }

  @POST
  @Path("/channel/create")
  public ChannelResponse createChannel(CreateChannelRequest createChannelRequest) {
    return dbService.createChannel(createChannelRequest);
  }

  @GET
  @Path("/channel/{channelId}/messages")
  public List<MessageResponse> getMessages(@PathParam("channelId") String channelId) {
    return dbService.getMessages(channelId);
  }

  @GET
  @Path("/message/{messageId}")
  public MessageResponse getMessage(@PathParam("messageId") String messageId) {
    return dbService.getMessage(messageId);
  }

  @GET
  @Path("/channels")
  public List<ChannelResponse> getChannels() {
    return dbService.getChannels();
  }

  @GET
  @Path("/channel/{channelId}")
  public ChannelResponse getChannel(@PathParam("channelId") String channelId) {
    return dbService.getChannel(channelId);
  }
}
