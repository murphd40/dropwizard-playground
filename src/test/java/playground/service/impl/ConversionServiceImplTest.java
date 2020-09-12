package playground.service.impl;

import java.util.Date;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import playground.api.response.MessageResponse;
import playground.dao.model.Message;
import playground.service.ConversionService;

class ConversionServiceImplTest {

  private ConversionService conversionService;

  @BeforeEach
  void init() {
    conversionService = new ConversionServiceImpl();
  }

  @Test
  void genericConversionTest() {
    Message message = Message.builder()
        .id("message-id")
        .channelId("channel-id")
        .content("message-content")
        .created(new Date())
        .updated(new Date(10000L))
        .build();

    MessageResponse result = conversionService.convert(message, MessageResponse.class);

    Assertions.assertThat(result).isEqualToComparingFieldByField(message);
  }

}