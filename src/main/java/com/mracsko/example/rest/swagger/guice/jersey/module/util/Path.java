package com.mracsko.example.rest.swagger.guice.jersey.module.util;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

public class Path {

  private final String path;

  public Path(final String path) {
    if (path == null) {
      throw new IllegalArgumentException("Parameter 'path' cannot be null");
    }

    this.path = path;
  }

  public String getPath() {
    return path;
  }

  public Path extend(String path) {
    if (this.path.endsWith("/") && path.startsWith("/")) {
      return new Path(this.path+path.substring(1));
    } else if (!this.path.endsWith("/") && !path.startsWith("/")) {
      return new Path(this.path+"/"+path);
    } else {
      return new Path(this.path+path);
    }
  }

  public Path extend(Path path) {
    return extend(path.getPath());
  }

  @Override
  public boolean equals(@Nullable final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Path)) {
      return false;
    }
    final Path path1 = (Path) o;
    return Objects.equals(path, path1.path);
  }

  @Override
  public int hashCode() {
    return Objects.hash(path);
  }
}
