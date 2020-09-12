package playground.configuration.binding;

import com.google.inject.AbstractModule;
import playground.dao.client.DbClient;
import playground.dao.client.mock.MockDbClient;
import playground.service.ConversionService;
import playground.service.impl.ConversionServiceImpl;

public class DbServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(DbClient.class).to(MockDbClient.class);
    bind(ConversionService.class).to(ConversionServiceImpl.class);
  }
}
