/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.whitepaperservice.Address;
import ws.whitepaperservice.Person;
import ws.whitepaperservice.PersonExistsFault;

/**
 *
 * @author dxong
 */
public class whitepageWebserviceUnitTest {
    
    public whitepageWebserviceUnitTest() {
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

    
    @Test
    public void addPerson_AddOnePerson_ReturnsStringDone() {
        //Arrange
        ws.whitepaperservice.Person TisseMand = new Person();
        TisseMand.setFirstName("Dillermand2");
        TisseMand.setLastName("Til Farmand2");
        TisseMand.setPhone("66666662");
        Address redlightdistrict = new Address();
        redlightdistrict.setStreet("DinMorGade 069");
        redlightdistrict.setPostcode("6662");
        redlightdistrict.setCity("DitHul");
        TisseMand.setAddress(redlightdistrict);
        
        String expectedResult = "done";
        String Result = "";
        try {
            //Act
            Result = addPerson(TisseMand);          
        } catch (PersonExistsFault ex) {
            System.out.println(ex.getFaultInfo());
            //Logger.getLogger(whitepageWebserviceUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Assert
        assertEquals(expectedResult, Result);
    }
    
    @Test
    public void addPerson_AddIdenticalPersons_ReturnsStringDone() {
        //Arrange
        ws.whitepaperservice.Person TisseMand = new Person();
        TisseMand.setFirstName("Dillermand");
        TisseMand.setLastName("Til Farmand");
        TisseMand.setPhone("66666666");
        Address redlightdistrict = new Address();
        redlightdistrict.setStreet("DinMorGade 69");
        redlightdistrict.setPostcode("6666");
        redlightdistrict.setCity("DitHul");
        TisseMand.setAddress(redlightdistrict);
        
        //String expectedResult = "done";
        //String Result = "";
        String ResultfirstName = "";
        String ResultlastName = "";
        String ResultCity = "";
        String FaultString = "tis";
        try {
            //Act
            addPerson(TisseMand);
            addPerson(TisseMand);
        } catch (PersonExistsFault ex) {
            System.out.println(ex.getFaultInfo().getFaultstring());
            FaultString = ex.getFaultInfo().getDetail().getErrorMessage();
            ResultfirstName = ex.getFaultInfo().getDetail().getPerson().getFirstName();
            ResultlastName = ex.getFaultInfo().getDetail().getPerson().getLastName();
            ResultCity = ex.getFaultInfo().getDetail().getPerson().getAddress().getCity();
            assertEquals(TisseMand.getFirstName(), ResultfirstName);
            assertEquals(TisseMand.getLastName(), ResultlastName);
            assertEquals(TisseMand.getAddress().getCity(), ResultCity);
            
            //Logger.getLogger(whitepageWebserviceUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(FaultString);
        //Assert
        //assertEquals(expectedResult, Result);
    }

    private static String addPerson(ws.whitepaperservice.Person person) throws PersonExistsFault {
        ws.whitepaperservice.WhitepaperServiceService service = new ws.whitepaperservice.WhitepaperServiceService();
        ws.whitepaperservice.WhitepaperServicePortType port = service.getWhitepaperServicePortTypeBindingPort();
        return port.addPerson(person);
    }
}
