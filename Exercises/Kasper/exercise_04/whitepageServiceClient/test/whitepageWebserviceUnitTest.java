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
import ws.whitepaperservice.PersonType;
import ws.whitepaperservice.PersonArray;
import ws.whitepaperservice.PersonExistsFault;

/**
 *
 * @author dxong
 * 
 * 
 * WARNING: Non unique body parts! In a port, as per BP 1.1 R2710 operations 
 * must have unique operation signature on the wire for successful dispatch.
 * Methods [findPerson, addPerson] have the same request body block
 * {http://whitepaperService.ws}PersonFromType. Method dispatching may fail,
 * runtime will try to dispatch using SOAPAction. Another option is to enable
 * AddressingFeature to enabled runtime to uniquely identify WSDL operation 
 * using wsa:Action header.
 * 
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
        ws.whitepaperservice.PersonType TisseMand = new PersonType();
        TisseMand.setFirstName("Dillermand2ksnfkasdasdj");
        TisseMand.setLastName("Til Farmand");
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
        ws.whitepaperservice.PersonType TisseMand = new PersonType();
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
    
    @Test
    public void findPerson_findSpecificPersonByLastName_ReturnsCountOfTwoPersons() throws PersonExistsFault{
        //Arrange
        
        //Common Address for Person 1 and 2
        Address redlightdistrict = new Address();
        redlightdistrict.setStreet("Skodsborgvej 190");
        redlightdistrict.setPostcode("2850");
        redlightdistrict.setCity("Nærum");
        
        //Person 1
        ws.whitepaperservice.PersonType TisseMand = new PersonType();
        TisseMand.setFirstName("Jacob");
        TisseMand.setLastName("Tissekone");
        TisseMand.setPhone("66666666");
        TisseMand.setAddress(redlightdistrict);
        
        //Person 2
        ws.whitepaperservice.PersonType TisseMand2 = new PersonType();
        TisseMand2.setFirstName("Jacob");
        TisseMand2.setLastName("Diller");
        TisseMand2.setPhone("66666666");
        TisseMand2.setAddress(redlightdistrict);
        
        int expectedCount = 2;
        //Act
        addPerson(TisseMand);
        addPerson(TisseMand2);
        
        ws.whitepaperservice.PersonArray Result = findPerson(TisseMand);
        //Assert
        assertEquals(expectedCount, Result.getPerson().size());
        
    }

    private static String addPerson(ws.whitepaperservice.PersonType person) throws PersonExistsFault {
        ws.whitepaperservice.WhitepaperServiceService service = new ws.whitepaperservice.WhitepaperServiceService();
        ws.whitepaperservice.WhitepaperServicePortType port = service.getWhitepaperServicePort();
        return port.addPerson(person);
    }

    private static PersonArray findPerson(ws.whitepaperservice.PersonType person) {
        ws.whitepaperservice.WhitepaperServiceService service = new ws.whitepaperservice.WhitepaperServiceService();
        ws.whitepaperservice.WhitepaperServicePortType port = service.getWhitepaperServicePort();
        return port.findPerson(person);
    }
    
}
