package ws.testhelloworld;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestHelloWorld {
     @Test
     public void hello() {
         ws.helloworld.HelloWorldServiceService service = new ws.helloworld.HelloWorldServiceService();
         ws.helloworld.HelloWorldService port = service.getHelloWorldServicePort();
         // TODO initialize WS operation arguments here
         java.lang.String arg0 = "world";
         // TODO process result here
         java.lang.String result = port.helloWorld(arg0);
         assertEquals("Hello world", result);
     }
}