
import orderdata.CustomerType;
import orderdata.OrderType;
import orderdata.CreditcardType;
import orderdata.OrderType.Item;
import orderprocess.OrderPT;
import orderprocess.OrderProcessService;
import orderprocess.ReceiveOrderFault;
import orderprocess.ResultType;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestOrderProcess {

    OrderProcessService service;
    OrderPT port;
    CustomerType customer1;
    OrderType order1;
    CustomerType customer2;
    OrderType order2;
    CreditcardType creditcard1;
    CreditcardType creditcard2;
    ResultType result;
    OrderType emptyOrder;

    @Before
    public void setUp() {
        service = new orderprocess.OrderProcessService();
        port = service.getOrderPTBindingPort();
        customer1 = createCustomer("1", "Johan");
        order1 = createOrder("1");
        Item item = new Item();
        item.setName("iPhone 5");
        item.setQuantity(1);
        order1.getItem().add(item);
        customer2 = createCustomer("2", "Kerstin");
        order2 = createOrder("3");
        order2.getItem().add(item);
        creditcard1 = createCreditCard(customer1, "1234");
        creditcard2 = createCreditCard(customer2, "4321");
        emptyOrder = createOrder("0");
        result = null;

    }

    @Test
    public void testShipSuccess() throws ReceiveOrderFault {
        order1.setId("1");
        result = port.receiveOrder(customer1, order1);
        assertResultStatus("ok", result, customer1, order1);
        CreditcardType creditcard = createCreditCard(customer1, "1234");
        result = port.pay(customer1.getId(), order1.getId(), creditcard);
        assertResultStatus("ok", result, customer1, order1);
    }

    /*
     * This test will fail at first.
     * You should fix the OrderProcess BPEL process such that the test passes.
     */
    @Test
    public void testShipFailure() throws ReceiveOrderFault {
        order1.setId("2");
        result = port.receiveOrder(customer2, order1);
        assertResultStatus("ok", result, customer2, order1);
        CreditcardType creditcard = createCreditCard(customer2, "2345");
        result = port.pay(customer2.getId(), order1.getId(), creditcard);
        assertResultStatus("payment refunded", result, customer2, order1);

    }
    
    private void assertResultStatus(String status, ResultType result, CustomerType customer, OrderType order) {
        assertEquals(status, result.getStatus());
        assertEquals(customer.getId(), result.getCustomemrID());
        assertEquals(order.getId(), result.getOrderID());
    }


    private CreditcardType createCreditCard(CustomerType customer1, String ccnumber) {
        CreditcardType creditcard = new CreditcardType();
        creditcard.setName(customer1.getName());
        creditcard.setNumber(ccnumber);
        return creditcard;
    }

    private CustomerType createCustomer(String customerID1, String customerName1) {
        CustomerType customer = new orderdata.CustomerType();
        customer.setId(customerID1);
        customer.setName(customerName1);
        return customer;
    }

    private OrderType createOrder(String id) {
        OrderType order = new orderdata.OrderType();
        order.setId(id);
        return order;
    }
}