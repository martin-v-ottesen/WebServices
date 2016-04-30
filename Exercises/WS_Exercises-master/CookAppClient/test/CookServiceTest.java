/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ws.cook.EggSmellsBadFault;
import static org.junit.Assert.*;

/**
 *
 * @author Wojtek
 */
public class CookServiceTest {

    public CookServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {

        ws.cook.CookServiceService service = new ws.cook.CookServiceService();
        ws.cook.CookService port = service.getCookServicePort();
        // TODO initialize WS operation arguments here
        int arg0 = 22;
        // TODO process result here

        try { // Call Web Service Operation
            boolean result = port.breakEgg(arg0);
            assertEquals(true, result);
        } catch (EggSmellsBadFault ex) {
            fail();
            }


        try { // Call Web Service Operation
            boolean result = port.breakEgg(arg0);
            fail();
        } catch (EggSmellsBadFault ex) {
            System.out.println(ex.getFaultInfo());
            }

    }
}
