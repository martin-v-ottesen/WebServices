/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.helloworldbpel.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kucharekm
 */
public class HelloWorldBPELTest {

    public HelloWorldBPELTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testHelloWorld() {

            ws.helloworld.HelloWorldService service = new ws.helloworld.HelloWorldService();
            ws.helloworld.HelloWorldPortType port = service.getHelloWorldPort();

            javax.xml.ws.Holder<java.lang.String> part1 = new javax.xml.ws.Holder<java.lang.String>();
            part1.value = "World";

            port.helloWorld(part1);

            assertEquals("Hello World", part1.value);

    }

}