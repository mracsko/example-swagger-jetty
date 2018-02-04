package com.mracsko.example.rest.swagger.guice.jersey.module;

import org.glassfish.jersey.servlet.ServletContainer;

public class ServletContainerWithSwaggerPaths {
  private final ServletContainer servletContainer;
  private final SwaggerPaths swaggerPaths;

  public ServletContainerWithSwaggerPaths(final ServletContainer servletContainer, final SwaggerPaths swaggerPaths) {
    this.servletContainer = servletContainer;
    this.swaggerPaths = swaggerPaths;
  }

  public ServletContainer getServletContainer() {
    return servletContainer;
  }

  public SwaggerPaths getSwaggerPaths() {
    return swaggerPaths;
  }
}
