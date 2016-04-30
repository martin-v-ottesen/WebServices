/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.cleaner;

import javax.jws.Oneway;
import javax.jws.WebService;

/**
 *
 * @author kucharekm
 */
@WebService()
public class CleanerService {

    public boolean washDishes(int numberOfDishes) {

        // some logic
        System.out.println("Washing the dishes...");
        return true;
    }

    @Oneway
    public void ventilateAir(int secondsToVentilate) {
        
        System.out.println("Ventilating for " + secondsToVentilate + " seconds...");
    }

}
