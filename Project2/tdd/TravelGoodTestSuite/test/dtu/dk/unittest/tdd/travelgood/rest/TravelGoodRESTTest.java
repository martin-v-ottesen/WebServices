/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd.travelgood.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author Martin
 */
public class TravelGoodRESTTest {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8080/TravleGoodREST/webresources/tg");

    public TravelGoodRESTTest() {
    }
    @Test
    public void hello() {
        String result = target.request().get(String.class);
        assertEquals(result,"DTU");
    }
    
    @Test
    public void EccoTest(){
        String startDestination = "Copenhagen";
        String endDestination = "Thailand";
        String startDate = "2016-04-16";
        String result = target.path("flight/" + startDestination + "/"+endDestination+"/"+startDate)
               .request().get(String.class);
        System.out.println(result);
    }
}
