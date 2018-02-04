package com.mracsko.example.rest.swagger.guice.jersey.module.util;

import io.swagger.config.Scanner;
import io.swagger.config.SwaggerConfig;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.Info;
import io.swagger.models.Swagger;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Based on https://danielflower.github.io/2016/04/01/Swagger-with-embedded-jetty-without-magic.html
 */
public class SwaggerJerseyUtil {

  /**
   * Static utility class.
   */
  private SwaggerJerseyUtil() {
  }

  public static void configureSwaggerForResourceConfig(final ResourceConfig resourceConfig, final Path basePath, final String title, final String version) {
    new SwaggerContextService()
            .withSwaggerConfig(createSwaggerConfig(basePath, title, version))
            .withScanner(createScanner(resourceConfig))
            .initConfig().initScanner();

    resourceConfig.packages(io.swagger.jaxrs.listing.ApiListingResource.class
            .getPackage().getName());
  }

  private static Scanner createScanner(final ResourceConfig resourceConfig) {
    return new Scanner() {
      private boolean prettyPrint;

      public Set<Class<?>> classes() {
        return resourceConfig.getInstances().stream()
                .map(Object::getClass)
                .collect(Collectors.toSet());
      }

      public boolean getPrettyPrint() {
        return prettyPrint;
      }

      public void setPrettyPrint(boolean b) {
        prettyPrint = b;
      }
    };
  }

  private static SwaggerConfig createSwaggerConfig(final Path basePath, final String title, final String version) {
    return new SwaggerConfig() {
      public Swagger configure(Swagger swagger) {
        Info info = new Info();
        info.setTitle(title);
        info.setVersion(version);
        swagger.setInfo(info);
        swagger.setBasePath(basePath.getPath());
        return swagger;
      }

      public String getFilterClass() {
        return null;
      }
    };
  }

}
