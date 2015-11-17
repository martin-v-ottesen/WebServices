/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shipper.ws;

import javax.jws.WebService;
import ws.shipper.ShipOrderFailure;

/**
 *
 * @author hub
 */
@WebService(serviceName = "shipperService", portName = "shipperPortTypeBindingPort", endpointInterface = "ws.shipper.ShipperPortType", targetNamespace = "http://shipper.ws", wsdlLocation = "WEB-INF/wsdl/ShipperImpl/shipper.wsdl")
public class ShipperImpl {

    public java.lang.String shipOrder(orderdata.CustomerType customer, orderdata.OrderType order) throws ShipOrderFailure {
        System.out.println(String.format("shipOrder(%s,%s)", customer.getName(),order.getId()));
        if (customer.getId().equals("2")) {
            throw new ShipOrderFailure("Failure during shipping","Failure during shipping");
        }
        return "OK";
    }

    public java.lang.String cancelShipment(java.lang.String customer, java.lang.String order) {
        System.out.println(String.format("cancelShipment(%s,%s)",customer,order));
        return "canceled";
    }

}
