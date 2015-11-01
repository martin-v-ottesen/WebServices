package dk.dtu.ws;

import org.junit.Test;
import static org.junit.Assert.*;

public class HelloWorldTest {

    @Test
    public void helloWorld() {
        String result = helloWorld("World");
        assertEquals("Hello World", result);
        
    }

    private static String helloWorld(java.lang.String name) {
        dk.dtu.ws.HeloWorldServiceService service = new dk.dtu.ws.HeloWorldServiceService();
        dk.dtu.ws.HeloWorldService port = service.getHeloWorldServicePort();
        return port.helloWorld(name);
    }
}
