package playground.dao.client.mock;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import playground.dao.client.DbClient;
import playground.dao.model.Channel;
import playground.dao.model.Message;

public class MockDbClient implements DbClient {

  private Map<String, Channel> channels = new HashMap<>();
  private Map<String, Message> messages = new HashMap<>();

  @Override
  public void createChannel(Channel channel) {
    channels.put(channel.getId(), channel);
  }

  @Override
  public Optional<Channel> getChannel(String channelId) {
    return Optional.ofNullable(channels.get(channelId));
  }

  @Override
  public List<Channel> getChannels() {
    return channels.values().stream()
        .sorted(Comparator.comparing(Channel::getCreated))
        .collect(Collectors.toList());
  }

  @Override
  public void createMessage(Message message) {
    messages.put(message.getId(), message);
  }

  @Override
  public Optional<Message> getMessage(String messageId) {
    return Optional.ofNullable(messages.get(messageId));
  }

  @Override
  public List<Message> getMessages(String channelId) {
    return messages.values().stream()
        .filter(message -> channelId.equals(message.getChannelId()))
        .sorted(Comparator.comparing(Message::getCreated))
        .collect(Collectors.toList());
  }
}
