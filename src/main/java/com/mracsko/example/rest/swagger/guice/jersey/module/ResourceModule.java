package com.mracsko.example.rest.swagger.guice.jersey.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mracsko.example.rest.swagger.api.DummyApi;
import com.mracsko.example.rest.swagger.api.DummyApiService;
import com.mracsko.example.rest.swagger.api.JacksonJsonProvider;
import com.mracsko.example.rest.swagger.api.LocalDateProvider;
import com.mracsko.example.rest.swagger.api.OffsetDateTimeProvider;
import com.mracsko.example.rest.swagger.api.RFC3339DateFormat;
import com.mracsko.example.rest.swagger.guice.jersey.module.util.SwaggerJerseyUtil;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class ResourceModule extends AbstractModule {

  public static final SwaggerPaths PATHS = new SwaggerPaths("/dummy", "/api");

  @Override
  protected void configure() {

  }

  @Provides
  public ServletContainerWithSwaggerPaths servletContainerWithPath(final DummyApi dummyApi) {
    ResourceConfig resourceConfig = new ResourceConfig();
    resourceConfig.registerInstances(new JacksonJsonProvider());
    resourceConfig.registerInstances(new LocalDateProvider());
    resourceConfig.registerInstances(new OffsetDateTimeProvider());
    resourceConfig.registerInstances(new RFC3339DateFormat());
    resourceConfig.registerInstances(dummyApi);

    SwaggerJerseyUtil.configureSwaggerForResourceConfig(resourceConfig, PATHS.getFullPath(), "Dummy example API", "1.0");

    return new ServletContainerWithSwaggerPaths(new ServletContainer(resourceConfig), PATHS);
  }

  @Provides
  public DummyApi dummyApi(final DummyApiService dummyApiService) {
    return new DummyApi(dummyApiService);
  }

}
