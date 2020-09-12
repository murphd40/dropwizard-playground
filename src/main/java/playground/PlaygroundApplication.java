package playground;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import playground.configuration.binding.ChatResourceModule;
import playground.health.TemplateHealthCheck;
import playground.resources.ChatResource;
import playground.configuration.binding.DbServiceModule;
import playground.resources.PlaygroundResource;

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

    final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());

    Injector injector = Guice.createInjector(new ChatResourceModule(), new DbServiceModule());

    ChatResource chatResource = injector.getInstance(ChatResource.class);

    environment.jersey().register(playgroundResource);
    environment.jersey().register(chatResource);
    environment.healthChecks().register("template", healthCheck);
  }
}
