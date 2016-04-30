
import javax.xml.bind.JAXBException;
import org.junit.Test;
import hello.Endpoint;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.activation.DataSource;
import static org.junit.Assert.*;

/**
 *
 * @author hub
 */
public class RESTEchoServiceTest {

    public RESTEchoServiceTest() {
    }

    @Test
    public void testEchoServiceGet() throws JAXBException, IOException {
        Endpoint.Hello endpoint = new Endpoint.Hello();
        String expectedGet = "hello world";
        DataSource resultGet = endpoint.getAsTextPlain();
        BufferedReader reader = new BufferedReader(new InputStreamReader(resultGet.getInputStream()));
        assertEquals(expectedGet, reader.readLine());
    }

    @Test
    public void testEchoServicePost() throws JAXBException, IOException {
        Endpoint.Hello endpoint = new Endpoint.Hello();
        String expectedPost = "hello Hubert";
        String input = "Hubert";
        StringDataSource dataSource = new StringDataSource(input);
        DataSource resultGet = endpoint.postAsTextPlain(dataSource);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resultGet.getInputStream()));
        assertEquals(expectedPost, reader.readLine());

    }
}

class StringDataSource implements DataSource {

    byte[] input = {};

    StringDataSource(String os) {
        this.input = os.getBytes();
    }

    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(input);
    }

    public OutputStream getOutputStream() throws IOException {
        return null;
    }

    public String getContentType() {
        return "text/plain";
    }

    public String getName() {
        return "input";
    }
}
