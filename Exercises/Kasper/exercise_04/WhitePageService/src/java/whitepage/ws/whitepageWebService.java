/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whitepage.ws;

import javax.jws.WebService;
import ws.whitepaperservice.PersonExistsFault;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import ws.whitepaperservice.PersonAlreadyExistsError;
import ws.whitepaperservice.PersonArray;
import ws.whitepaperservice.PersonExistsFault;
import ws.whitepaperservice.PersonType;
import ws.whitepaperservice.Wpfault;

/**
 *
 * @author dxong
 */
@WebService(serviceName = "whitepaperServiceService", portName = "whitepaperServicePort", endpointInterface = "ws.whitepaperservice.WhitepaperServicePortType", targetNamespace = "http://whitepaperService.ws", wsdlLocation = "WEB-INF/wsdl/whitepageWebService/whitepaperServiceWrapper.wsdl")
public class whitepageWebService {
    
    List<ws.whitepaperservice.PersonType> PersonList = new ArrayList<>();

    public java.lang.String addPerson(ws.whitepaperservice.PersonType person) throws ws.whitepaperservice.PersonExistsFault {
        if (person != null) {
            for (PersonType personlist : PersonList) {
                if (personlist.getFirstName().equals(person.getFirstName()) 
                        && personlist.getLastName().equals(person.getLastName())) {
                    PersonAlreadyExistsError personAlreadyExistsError = new PersonAlreadyExistsError();
                    Wpfault wpfault = new Wpfault();
                    wpfault.setErrorMessage("Person already exists");
                    wpfault.setPerson(person);
                    personAlreadyExistsError.setDetail(wpfault);
                    System.out.println("Exception");
                    throw new PersonExistsFault("Person already exists", personAlreadyExistsError );
                }
            }   
        }
        System.out.println("Successful");
        PersonList.add(person);
        return "done";
    }
    
    public ws.whitepaperservice.PersonArray findPerson(ws.whitepaperservice.PersonType person) {
        System.out.println("Calling findPerson with PersonName: " + person.getFirstName() + " " + person.getLastName());
        
        ws.whitepaperservice.PersonArray foundPersonsList = new PersonArray();
        
        System.out.println("PersonList Count: " + PersonList.size());
                
        if (person != null) {
            for (PersonType personList : PersonList) {
                if (personList.getFirstName().equalsIgnoreCase(person.getFirstName())
                        || personList.getLastName().equalsIgnoreCase(person.getLastName())
                        || personList.getAddress().getCity().equalsIgnoreCase(person.getAddress().getCity())) {
                    foundPersonsList.getPerson().add(personList);
                }
            }
        }
        return foundPersonsList;
        
    }
    
}
