/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.testhelloworldfromwsdl;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kucharekm
 */
public class TestHelloWorldFromWSDL {

    @Test
    public void hello() {

        ws.helloworld.HelloworldService service = new ws.helloworld.HelloworldService();
        ws.helloworld.HelloworldPortType port = service.getHelloworldPort();
        // TODO initialize WS operation arguments here
        java.lang.String part1 = "Timmy";
        // TODO process result here
        java.lang.String result = port.helloworld(part1);

        assertEquals("Hello Timmy", result);

    }
}
