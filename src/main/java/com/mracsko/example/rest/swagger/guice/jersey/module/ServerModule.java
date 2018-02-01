package com.mracsko.example.rest.swagger.guice.jersey.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class ServerModule extends AbstractModule {

  public static final String BASE_PATH = "/api/*";
  public static final int PORT = 8080;
  public static final String CONTEXT_PATH = "/";

  @Override
  protected void configure() {

  }

  @Provides
  public Server server(final ServletContainer servletContainer) {
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath(CONTEXT_PATH);

    Server jettyServer = new Server(PORT);
    jettyServer.setHandler(context);

    ServletHolder jerseyServlet = new ServletHolder(servletContainer);
    jerseyServlet.setInitOrder(0);

    context.addServlet(jerseyServlet, BASE_PATH);

    return jettyServer;
  }
}
