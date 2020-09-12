package playground.service;

public interface ConversionService {

  <T> T convert(Object source, Class<T> target);

}
