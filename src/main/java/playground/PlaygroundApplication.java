package playground;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import playground.dao.client.DbClient;
import playground.dao.client.mock.MockDbClient;
import playground.health.TemplateHealthCheck;
import playground.resources.ChatResource;
import playground.resources.PlaygroundResource;
import playground.service.ConversionService;
import playground.service.DbService;

public class PlaygroundApplication extends Application<PlaygroundConfiguration> {

  public static void main(String[] args) throws Exception {
    new PlaygroundApplication().run(args);
  }

  @Override
  public String getName() {
    return "hello-world";
  }

  @Override
  public void initialize(Bootstrap<PlaygroundConfiguration> bootstrap) {
    // no op
  }

  @Override
  public void run(PlaygroundConfiguration configuration, Environment environment) throws Exception {
    final PlaygroundResource playgroundResource =
        new PlaygroundResource(configuration.getTemplate(), configuration.getDefaultName());

    DbClient dbClient = new MockDbClient();
    ConversionService conversionService = new ConversionService();
    DbService dbService = new DbService(dbClient, conversionService);
    final ChatResource chatResource = new ChatResource(dbService);

    final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());

    environment.jersey().register(playgroundResource);
    environment.jersey().register(chatResource);
    environment.healthChecks().register("template", healthCheck);
  }
}
