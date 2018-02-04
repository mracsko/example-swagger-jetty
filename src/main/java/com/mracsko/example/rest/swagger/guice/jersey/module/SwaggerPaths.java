package com.mracsko.example.rest.swagger.guice.jersey.module;

import com.mracsko.example.rest.swagger.guice.jersey.module.util.Path;

public class SwaggerPaths {

  private final Path contextPath;
  private final Path apiPath;
  private final Path fullPath;

  public SwaggerPaths(final String contextPath, final String apiPath) {
    this(new Path(contextPath), new Path(apiPath));
  }

  public SwaggerPaths(final Path contextPath, final Path apiPath) {
    this.contextPath = contextPath;
    this.apiPath = apiPath;
    this.fullPath = contextPath.extend(apiPath);
  }

  public Path getContextPath() {
    return contextPath;
  }

  public Path getApiPath() {
    return apiPath;
  }

  public Path getFullPath() {
    return fullPath;
  }
}

