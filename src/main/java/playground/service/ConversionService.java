package playground.service;

import playground.api.response.ChannelResponse;
import playground.api.response.MessageResponse;
import playground.dao.model.Channel;
import playground.dao.model.Message;

public class ConversionService {

  public MessageResponse toMessageResponse(Message message) {
    return MessageResponse.builder()
        .id(message.getId())
        .channelId(message.getChannelId())
        .content(message.getContent())
        .created(message.getCreated())
        .updated(message.getUpdated())
        .build();
  }

  public ChannelResponse toChannelResponse(Channel channel) {
    return ChannelResponse.builder()
        .id(channel.getId())
        .name(channel.getName())
        .created(channel.getCreated())
        .updated(channel.getUpdated())
        .build();
  }
}
