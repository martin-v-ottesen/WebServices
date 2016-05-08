/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd.travelgood.bpel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dxong
 */
public class TravelGoodBPELJUnitTest {
    
    public TravelGoodBPELJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    private static java.util.List<dtu.dk.webservice.service.FlightInformation> getFlights(java.lang.String startDestination, java.lang.String endDestination, java.lang.String startDate) {
        dtu.dk.webservice.service.wsdl.travelgoodbpelmodule.src.travelgoodwsdl.TravelGoodWSDLService service = new dtu.dk.webservice.service.wsdl.travelgoodbpelmodule.src.travelgoodwsdl.TravelGoodWSDLService();
        dtu.dk.webservice.service.wsdl.travelgoodbpelmodule.src.travelgoodwsdl.TravelGoodWSDLPortType port = service.getTravelGoodWSDLPortTypeBindingPort();
        return port.getFlights(startDestination, endDestination, startDate);
    }    
}
