package week05;

import javax.ws.rs.*;

@Path("students/{id}")
public class StudentResource {

    public static Student student = null;

    public StudentResource() {
        StudentResource.student = new Student();
        StudentResource.student.setName("Jimmy Jones");
    }

    @GET
    public Student getStudent(@PathParam("id") String id) {
        student.setId(id);
        return student;
    }

    @PUT
    public Student setStudent(@PathParam("id") String id, Student std) {
        student = std;
        student.setId(id);
        return student;
    }

    @DELETE
    public String deleteStudent(@PathParam("id") String id) {
        return "deleted "+id+"\n";
    }
}