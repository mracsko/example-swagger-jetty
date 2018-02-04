package com.mracsko.example.rest.swagger.guice.jersey.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mracsko.example.rest.swagger.guice.jersey.Main;
import com.mracsko.example.rest.swagger.guice.jersey.module.util.Path;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerModule extends AbstractModule {

  private static final Logger log = LoggerFactory.getLogger(Main.class);


  public static final int PORT = 8080;

  @Override
  protected void configure() {
  }

  @Provides
  public Server server(final ServletContainerWithSwaggerPaths servletContainer) throws Exception {
    Server jettyServer = new Server(PORT);

    ServletContextHandler servletContext = createServletContext(servletContainer);
    ContextHandler swaggerUiContext = createSwaggerUiContextHandler(servletContainer.getSwaggerPaths().getContextPath());
    ContextHandler swaggerIndexHtmlContext = createSwaggerIndexHtmlContextHandler(servletContainer.getSwaggerPaths().getFullPath());

    HandlerList handlers = new HandlerList();
    handlers.setHandlers(new Handler[]{swaggerUiContext, swaggerIndexHtmlContext, servletContext, new DefaultHandler()});
    jettyServer.setHandler(handlers);

    log.info("Check Swagger documentation on endpoint: {}", servletContainer.getSwaggerPaths().getFullPath().getPath());

    return jettyServer;
  }

  private ServletContextHandler createServletContext(final ServletContainerWithSwaggerPaths servletContainer) {
    ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
    servletContext.setContextPath(servletContainer.getSwaggerPaths().getContextPath().getPath());

    ServletHolder jerseyServlet = new ServletHolder(servletContainer.getServletContainer());
    jerseyServlet.setInitOrder(0);

    Path servletMapping = servletContainer.getSwaggerPaths().getApiPath().extend(new Path("/*"));

    servletContext.addServlet(jerseyServlet, servletMapping.getPath());

    log.info("Added servlet with mapping: {}",servletMapping.getPath());
    log.info("Created Servlet context with path: {}",servletContext.getContextPath());

    return servletContext;
  }

  public ContextHandler createSwaggerUiContextHandler(final Path basePath) throws Exception {
    ResourceHandler resourceHandler = new ResourceHandler();
    resourceHandler.setResourceBase(getClass().getClassLoader()
            .getResource("META-INF/resources/webjars/swagger-ui/3.9.3")
            .toURI().toString());
    ContextHandler context = new ContextHandler();
    context.setContextPath(basePath.extend(new Path("/swagger-ui")).getPath());
    context.setHandler(resourceHandler);
    log.info("Created Swagger UI context with path: {}",context.getContextPath());
    return context;
  }

  public ContextHandler createSwaggerIndexHtmlContextHandler(Path basePath) throws Exception {
    ResourceHandler resourceHandler = new ResourceHandler();
    resourceHandler.setResourceBase(getClass().getClassLoader()
            .getResource("com/mracsko/example/rest/swagger/jersey/html/dummy")
            .toURI().toString());
    resourceHandler.setWelcomeFiles(new String[]{"index.html"});
    ContextHandler context = new ContextHandler();
    context.setContextPath(basePath.getPath());
    context.setHandler(resourceHandler);
    log.info("Created Swagger HTML for API context with path: {}",context.getContextPath());
    return context;
  }


}