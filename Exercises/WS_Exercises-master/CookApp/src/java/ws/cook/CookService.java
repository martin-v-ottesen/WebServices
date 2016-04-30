/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.cook;

import javax.jws.WebService;

/**
 *
 * @author kucharekm
 */
@WebService()
public class CookService {

    private static int eggCounter = 1;

    public boolean breakEgg(int numberOfEggs) throws EggSmellsBadFault {

        if (eggCounter == 2) {
            eggCounter = 1;
            throw new EggSmellsBadFault("error", "eggSmellsBad");
        } else {
            eggCounter++;
        }
        // some logic
        System.out.println("Breaking the eggs...");

        return true;
    }

    public boolean bake(int secondsToBake) {

        System.out.println("Baking the omlet (" + secondsToBake + " seconds)...");

        return true;
    }
}
