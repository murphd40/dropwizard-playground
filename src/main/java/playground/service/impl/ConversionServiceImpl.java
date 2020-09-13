package playground.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import playground.service.ConversionService;

public class ConversionServiceImpl implements ConversionService {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public <T> T convert(Object source, Class<T> target) {
    return objectMapper.convertValue(source, target);
  }
}
