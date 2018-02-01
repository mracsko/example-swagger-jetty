package com.mracsko.example.rest.swagger.guice.jersey.module;

import com.google.inject.AbstractModule;
import com.mracsko.example.rest.swagger.api.DummyApiService;
import com.mracsko.example.rest.swagger.guice.jersey.service.DefaultDummyApiService;

public class ServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(DummyApiService.class).to(DefaultDummyApiService.class);
  }
}
