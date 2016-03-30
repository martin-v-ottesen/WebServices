/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whitepage.ws;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import ws.whitepaperservice.Person;
import ws.whitepaperservice.PersonAlreadyExistsError;
import ws.whitepaperservice.PersonExistsFault;
import ws.whitepaperservice.Wpfault;

/**
 *
 * @author dxong
 */
@WebService(serviceName = "whitepaperServiceService", portName = "whitepaperServicePortTypeBindingPort", endpointInterface = "ws.whitepaperservice.WhitepaperServicePortType", targetNamespace = "http://whitepaperService.ws", wsdlLocation = "WEB-INF/wsdl/whitepageWebService/whitepaperService.wsdl")
public class whitepageWebService {
    List<ws.whitepaperservice.Person> PersonList = new ArrayList<>();

    public java.lang.String addPerson(ws.whitepaperservice.Person person) throws PersonExistsFault {
        
        if (person != null) {
            for (Person personlist : PersonList) {
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
    
}
