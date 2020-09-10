package playground.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import playground.api.request.CreateChannelRequest;
import playground.api.request.CreateMessageRequest;
import playground.api.response.ChannelResponse;
import playground.api.response.MessageResponse;
import playground.dao.client.DbClient;
import playground.dao.model.Channel;
import playground.dao.model.Message;

public class DbService {

  private final DbClient dbClient;
  private final ConversionService conversionService;

  public DbService(DbClient dbClient, ConversionService conversionService) {
    this.dbClient = dbClient;
    this.conversionService = conversionService;
  }

  public MessageResponse createMessage(CreateMessageRequest createMessageRequest) {

    dbClient
        .getChannel(createMessageRequest.getChannelId())
        .orElseThrow(() -> new RuntimeException("Channel not found"));

    Date currentDate = new Date();
    Message message =
        Message.builder()
            .id(UUID.randomUUID().toString())
            .channelId(createMessageRequest.getChannelId())
            .content(createMessageRequest.getContent())
            .created(currentDate)
            .updated(currentDate)
            .build();

    dbClient.createMessage(message);

    return conversionService.toMessageResponse(message);
  }

  public MessageResponse getMessage(String messageId) {
    return dbClient
        .getMessage(messageId)
        .map(conversionService::toMessageResponse)
        .orElseThrow(() -> new RuntimeException("Message not found"));
  }

  public List<MessageResponse> getMessages(String channelId) {
    return dbClient.getMessages(channelId).stream()
        .map(conversionService::toMessageResponse)
        .collect(Collectors.toList());
  }

  public ChannelResponse createChannel(CreateChannelRequest createChannelRequest) {
    Date currentDate = new Date();
    Channel channel =
        Channel.builder()
            .id(UUID.randomUUID().toString())
            .name(createChannelRequest.getName())
            .created(currentDate)
            .updated(currentDate)
            .build();

    dbClient.createChannel(channel);

    return conversionService.toChannelResponse(channel);
  }

  public ChannelResponse getChannel(String channelId) {
    return dbClient
        .getChannel(channelId)
        .map(conversionService::toChannelResponse)
        .orElseThrow(() -> new RuntimeException("Channel not found"));
  }

  public List<ChannelResponse> getChannels() {
    return dbClient.getChannels().stream()
        .map(conversionService::toChannelResponse)
        .collect(Collectors.toList());
  }
}
