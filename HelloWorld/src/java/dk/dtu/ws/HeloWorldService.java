package dk.dtu.ws;

@javax.jws.WebService
public class HeloWorldService {

    public String helloWorld(String txt) {
        return "Hello " + txt;
    }
}
