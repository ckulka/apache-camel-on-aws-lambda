package cc.ic3.apacheCamelOnAWSLambda;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws.s3.S3Constants;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {

  public void configure() {
    from("direct:start")
        .id("My Route")
        .to("sql:classpath:query.sql")
        .log("Found ${body.size()} results")
        .marshal().csv()
        .setHeader(Exchange.FILE_NAME, simple("test-${date:now:yyyyMMdd-hhmmss}.csv"))
        .setHeader(S3Constants.KEY, simple("test-${date:now:yyyyMMdd-hhmmss}.csv"))
        .to("{{uploadEndpoint}}")
        .log("Uploaded file ${headers.CamelFileName}");
  }
}
