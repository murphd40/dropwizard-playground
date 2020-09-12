package playground.configuration.binding;

import com.google.inject.AbstractModule;
import playground.service.DbService;
import playground.service.impl.DbServiceImpl;

public class ChatResourceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(DbService.class).to(DbServiceImpl.class);
  }
}
