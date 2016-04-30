/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.concat;

import javax.jws.WebService;

/**
 *
 * @author kucharekm
 */
@WebService()
public class ConcatService {

    public String concat(String input1, String input2) {
        return input1 + input2;
    }

}
