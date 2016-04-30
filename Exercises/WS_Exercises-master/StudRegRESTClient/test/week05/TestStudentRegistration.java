/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package week05;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.activation.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hub
 */
public class TestStudentRegistration {
    @Test
    public void findStudent() throws JAXBException, IOException {
        Endpoint.Students students = new Endpoint.Students();
        DataSource result = students.getAs("Jimmy");
        assertEquals("http://localhost:8080/sr/resources/students/123",readString(result));
    }
    
    @Test
    public void registerStudent() throws JAXBException, IOException {
        Endpoint.Students students = new Endpoint.Students();
        Student student = new Student();
        student.setId("133");
        student.setName("Jimmy Jones");
        DataSource result = students.postAs(marshal(student));
        assertEquals("http://localhost:8080/sr/resources/students/133",readString(result));
    }

    @Test
    public void getStudent() throws JAXBException, IOException {
        Endpoint.StudentsId studentsId = new Endpoint.StudentsId("123");
        DataSource result = studentsId.getAs();
        Student student = unmarshal(result);
        assertEquals("123",student.getId());
    }

    @Test
    public void updateStudent() throws JAXBException, IOException {
        Student student = new Student();
        student.setId("123");
        student.setName("Jones, Jimmy");
        Endpoint.StudentsId studentsId = new Endpoint.StudentsId("123");
        DataSource result = studentsId.putAs(marshal(student));
        Student s = unmarshal(result);
        assertEquals("Jones, Jimmy",s.getName());
    }

    @Test
    public void deleteStudent() throws JAXBException, IOException {
        Endpoint.StudentsId studentsId = new Endpoint.StudentsId("123");
        DataSource result = studentsId.deleteAs();
        assertEquals("deleted 123",readString(result));
    }

    private <T> StringDataSource marshal(T o) throws JAXBException {
        Marshaller m = JAXBContext.newInstance(Student.class).createMarshaller();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        m.marshal(o, os);
        StringDataSource dataSource = new StringDataSource(os.toByteArray());
        return dataSource;
    }

    private String readString(DataSource resultGet) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(resultGet.getInputStream()));
        return reader.readLine();
    }

    private <T> T unmarshal(DataSource resultGet) throws IOException, JAXBException {
        Unmarshaller um = JAXBContext.newInstance(Student.class).createUnmarshaller();
        BufferedReader reader = new BufferedReader(new InputStreamReader(resultGet.getInputStream()));
        T or = (T) um.unmarshal(reader);
        return or;
    }

    class StringDataSource implements DataSource {

        byte[] input = {};

        StringDataSource(byte[] os) {
            this.input = os;
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
}

