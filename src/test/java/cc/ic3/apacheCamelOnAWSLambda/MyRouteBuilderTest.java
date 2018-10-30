package cc.ic3.apacheCamelOnAWSLambda;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;

import java.nio.file.Files;

@RunWith(CamelSpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class MyRouteBuilderTest {

  @Autowired
  protected CamelContext camelContext;

  @EndpointInject(uri = "{{uploadEndpoint}}")
  private MockEndpoint mock;

  @Produce(uri = "direct:start")
  private ProducerTemplate start;

  @Value("classpath:expected.csv")
  private Resource expected;

  @Test
  public void testPositive() throws Exception {
    mock.expectedBodiesReceived(new String(Files.readAllBytes(expected.getFile().toPath())));

    start.sendBody("");

    MockEndpoint.assertIsSatisfied(camelContext);
  }
}