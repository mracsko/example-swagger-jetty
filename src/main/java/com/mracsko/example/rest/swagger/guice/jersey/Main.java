package com.mracsko.example.rest.swagger.guice.jersey;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mracsko.example.rest.swagger.guice.jersey.module.ResourceModule;
import com.mracsko.example.rest.swagger.guice.jersey.module.ServerModule;
import com.mracsko.example.rest.swagger.guice.jersey.module.ServiceModule;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  public static void main(String... args) throws Exception {
    Injector injector = Guice.createInjector(new ServiceModule(), new ResourceModule(), new ServerModule());

    Server jettyServer = injector.getInstance(Server.class);

    jettyServer.start();
    jettyServer.join();
  }
}
