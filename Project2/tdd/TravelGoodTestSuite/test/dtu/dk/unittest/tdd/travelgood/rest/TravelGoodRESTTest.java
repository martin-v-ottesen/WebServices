/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd.travelgood.rest;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author Martin
 */
public class TravelGoodRESTTest {

    @Test
    public void hello() {
        Client client = Client.create();
        WebResource r = client.resource("http://localhost:8080/TG/webresources/tg");
        String result = r.get(String.class);
        assertEquals(result,"DTU");
    }
}
