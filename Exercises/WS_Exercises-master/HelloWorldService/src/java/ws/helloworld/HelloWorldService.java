package ws.helloworld;

import javax.jws.WebService;

@WebService()
public class HelloWorldService {
    public String helloWorld(String input) {
       return "Hello " + input;
    }
}
