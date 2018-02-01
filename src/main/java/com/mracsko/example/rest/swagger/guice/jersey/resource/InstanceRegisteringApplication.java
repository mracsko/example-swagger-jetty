package com.mracsko.example.rest.swagger.guice.jersey.resource;

import com.mracsko.example.rest.swagger.api.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.List;

public class InstanceRegisteringApplication extends ResourceConfig {

  public InstanceRegisteringApplication(List<Object> resources) {
    for (Object resource : resources) {
      registerInstances(resource);
    }
  }

}