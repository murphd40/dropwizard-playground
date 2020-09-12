package playground.service;

import java.util.List;
import playground.api.request.CreateChannelRequest;
import playground.api.request.CreateMessageRequest;
import playground.api.response.ChannelResponse;
import playground.api.response.MessageResponse;

public interface DbService {

  MessageResponse createMessage(CreateMessageRequest createMessageRequest);

  MessageResponse getMessage(String messageId);

  List<MessageResponse> getMessages(String channelId);

  ChannelResponse createChannel(CreateChannelRequest createChannelRequest);

  ChannelResponse getChannel(String channelId);

  List<ChannelResponse> getChannels();

}
