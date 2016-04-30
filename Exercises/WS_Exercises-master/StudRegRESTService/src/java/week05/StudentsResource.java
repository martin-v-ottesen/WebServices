package week05;

import javax.ws.rs.*;

@Path("students")
public class StudentsResource {
    @POST
    public String registerStudent(Student st) {
        String url = "http://localhost:8080/sr/resources/students/";
        return url+st.getId()+"\n";
    }

    @GET
    public String findStudent(@QueryParam("name") String name) {
        String url = "http://localhost:8080/sr/resources/students/"+"123"+"\n";
        return url;
    }
}