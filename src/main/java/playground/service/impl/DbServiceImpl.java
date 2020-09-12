package playground.service.impl;

import com.google.inject.Inject;
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
import playground.service.ConversionService;
import playground.service.DbService;

public class DbServiceImpl implements DbService {

  private final DbClient dbClient;
  private final ConversionService conversionService;

  @Inject
  public DbServiceImpl(DbClient dbClient, ConversionService conversionService) {
    this.dbClient = dbClient;
    this.conversionService = conversionService;
  }

  @Override
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

    return conversionService.convert(message, MessageResponse.class);
  }

  @Override
  public MessageResponse getMessage(String messageId) {
    return dbClient
        .getMessage(messageId)
        .map(message -> conversionService.convert(message, MessageResponse.class))
        .orElseThrow(() -> new RuntimeException("Message not found"));
  }

  @Override
  public List<MessageResponse> getMessages(String channelId) {
    return dbClient.getMessages(channelId).stream()
        .map(message -> conversionService.convert(message, MessageResponse.class))
        .collect(Collectors.toList());
  }

  @Override
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

    return conversionService.convert(channel, ChannelResponse.class);
  }

  @Override
  public ChannelResponse getChannel(String channelId) {
    return dbClient
        .getChannel(channelId)
        .map(channel -> conversionService.convert(channel, ChannelResponse.class))
        .orElseThrow(() -> new RuntimeException("Channel not found"));
  }

  @Override
  public List<ChannelResponse> getChannels() {
    return dbClient.getChannels().stream()
        .map(channel -> conversionService.convert(channel, ChannelResponse.class))
        .collect(Collectors.toList());
  }

}
