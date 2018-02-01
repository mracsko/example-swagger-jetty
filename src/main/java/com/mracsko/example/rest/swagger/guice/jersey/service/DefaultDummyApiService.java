package com.mracsko.example.rest.swagger.guice.jersey.service;

import com.mracsko.example.rest.swagger.api.DummyApiService;
import com.mracsko.example.rest.swagger.api.NotFoundException;
import com.mracsko.example.rest.swagger.model.Dummy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public class DefaultDummyApiService implements DummyApiService {

  private static final Logger log = LoggerFactory.getLogger(DefaultDummyApiService.class);

  @Override
  public Response getDummy(final SecurityContext securityContext) throws NotFoundException {
    Dummy dummy = new Dummy();
    dummy.setDate(LocalDate.now());
    dummy.setDatetime(OffsetDateTime.now());
    dummy.setNumber(10L);
    dummy.setString("string");

    log.info("Get method is called, returning {}", dummy);

    return Response.ok().entity(dummy).build();
  }

  @Override
  public Response putDummy(final Dummy dummy, final SecurityContext securityContext) throws NotFoundException {
    log.info("Put method is called, parameter {}", dummy);

    return Response.ok().build();
  }
}
