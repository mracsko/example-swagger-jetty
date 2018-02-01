package com.mracsko.example.rest.swagger.guice.jersey.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mracsko.example.rest.swagger.api.DummyApi;
import com.mracsko.example.rest.swagger.api.DummyApiService;
import com.mracsko.example.rest.swagger.api.JacksonJsonProvider;
import com.mracsko.example.rest.swagger.api.LocalDateProvider;
import com.mracsko.example.rest.swagger.api.OffsetDateTimeProvider;
import com.mracsko.example.rest.swagger.api.RFC3339DateFormat;
import com.mracsko.example.rest.swagger.guice.jersey.resource.InstanceRegisteringApplication;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.ArrayList;
import java.util.List;

public class ResourceModule extends AbstractModule {

  @Override
  protected void configure() {

  }

  @Provides
  public ServletContainer servletContainer(final DummyApi dummyApi) {
    List<Object> resources = new ArrayList<>();

    resources.add(new JacksonJsonProvider());
    resources.add(new LocalDateProvider());
    resources.add(new OffsetDateTimeProvider());
    resources.add(new RFC3339DateFormat());
    resources.add(dummyApi);

    return new ServletContainer(new InstanceRegisteringApplication(resources));
  }

  @Provides
  public DummyApi dummyApi(final DummyApiService dummyApiService) {
    return new DummyApi(dummyApiService);
  }
}
