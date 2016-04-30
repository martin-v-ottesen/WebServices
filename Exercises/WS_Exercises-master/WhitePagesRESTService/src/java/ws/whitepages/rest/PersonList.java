/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.whitepages.rest;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mkucharek
 */
@XmlRootElement
public class PersonList {

    @XmlElement(name="person")
    public List<Person> persons;

    public PersonList(List<Person> personList) {
        this.persons = personList;
    }
}
