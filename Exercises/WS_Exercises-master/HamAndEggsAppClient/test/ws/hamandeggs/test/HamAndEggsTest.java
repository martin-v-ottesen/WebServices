/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.hamandeggs.test;

import java.util.Random;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kucharekm
 */
public class HamAndEggsTest {

    private final int EGG_LIMIT = 10;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

     @Test
     public void testHamAndEggs() {

             ws.hamandeggs.HamAndEggsService service = new ws.hamandeggs.HamAndEggsService();
             ws.hamandeggs.HamAndEggsPortType port = service.getHamAndEggsPort();

             // TODO initialize WS operation arguments here
             Random rand = new Random();
             int input = rand.nextInt(EGG_LIMIT);

             // TODO process result here
             int result = port.hamAndEggsOperation(input);

             if (input % 2 == 0) {
                assertEquals(input/2, result);
             } else {
                 assertTrue(((input-1)/2 == result) || ((input+1)/2 == result));
             }


     }

}