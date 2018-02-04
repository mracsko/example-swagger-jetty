package com.mracsko.example.rest.swagger.guice.jersey.module.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PathTest {

  @Test
  public void when_extendWithStartingAndEndingSlash_then_Success() {
    // Given
    final Path path1 = new Path("/one/");
    final Path path2 = new Path("/two/");
    final Path expected = new Path("/one/two/");

    // When
    final Path actual = path1.extend(path2);

    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void when_extendWithEndingSlash_then_Success() {
    // Given
    final Path path1 = new Path("/one/");
    final Path path2 = new Path("two/");
    final Path expected = new Path("/one/two/");

    // When
    final Path actual = path1.extend(path2);

    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void when_extendWithStartingSlash_then_Success() {
    // Given
    final Path path1 = new Path("/one");
    final Path path2 = new Path("/two/");
    final Path expected = new Path("/one/two/");

    // When
    final Path actual = path1.extend(path2);

    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void when_extendWithoutStartingAndEndingSlash_then_Success() {
    // Given
    final Path path1 = new Path("/one");
    final Path path2 = new Path("two/");
    final Path expected = new Path("/one/two/");

    // When
    final Path actual = path1.extend(path2);

    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void when_extendSlashPathWithStartingSlash_then_Success() {
    // Given
    final Path path1 = new Path("/");
    final Path path2 = new Path("/two/");
    final Path expected = new Path("/two/");

    // When
    final Path actual = path1.extend(path2);

    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void when_extendSlashPathWithoutStartingSlash_then_Success() {
    // Given
    final Path path1 = new Path("/");
    final Path path2 = new Path("two/");
    final Path expected = new Path("/two/");

    // When
    final Path actual = path1.extend(path2);

    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void when_extendPathWithSlashEndingWithSlash_then_Success() {
    // Given
    final Path path1 = new Path("/one/");
    final Path path2 = new Path("/");
    final Path expected = new Path("/one/");

    // When
    final Path actual = path1.extend(path2);

    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void when_extendPathWithoutSlashEndingWithSlash_then_Success() {
    // Given
    final Path path1 = new Path("/one");
    final Path path2 = new Path("/");
    final Path expected = new Path("/one/");

    // When
    final Path actual = path1.extend(path2);

    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void when_extendSlashPathWithSlashPath_then_Success() {
    // Given
    final Path path1 = new Path("/");
    final Path path2 = new Path("/");
    final Path expected = new Path("/");

    // When
    final Path actual = path1.extend(path2);

    // Then
    assertEquals(expected, actual);
  }
}