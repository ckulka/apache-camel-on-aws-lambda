package cc.ic3.apacheCamelOnAWSLambda;

import org.apache.camel.CamelContext;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyCamelApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyCamelApplication.class)
        .getBean(CamelContext.class)
        .createProducerTemplate()
        .sendBody("direct:start", "");
  }
}
