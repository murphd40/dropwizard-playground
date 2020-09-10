package playground.dao.client;

import java.util.List;
import java.util.Optional;
import playground.dao.model.Channel;
import playground.dao.model.Message;

public interface DbClient {

  void createChannel(Channel channel);

  Optional<Channel> getChannel(String channelId);

  List<Channel> getChannels();

  void createMessage(Message message);

  Optional<Message> getMessage(String messageId);

  List<Message> getMessages(String channelId);
}
