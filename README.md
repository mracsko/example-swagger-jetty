# Swagger Jetty Example

TBD

## Build and Run
Example Application can be built and started with the following command: `gradlew run`
Do not forget to set `JAVA_HOME` environment variable to a JDK 1.8 path.

After the application is started, Swagger documentation and REST endpoints are available on the following url: http://localhost:8080/dummy/api/

## Creating index.html
* index.html is created by copying the html from swagger ui and modify all urls
    * In the example from _dummy/api_ to _dummy/swagger-ui_ this have been done by replacing _./_ to _../swagger-ui/_
    * Modify url to swagger.json in the script (_SwaggerUIBundle_) 
