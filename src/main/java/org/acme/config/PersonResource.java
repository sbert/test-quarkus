package org.acme.config;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;

@Path("/api/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GET
    public List<Person> list() {
        return Person.findAll().list();
    }

    @Path("/init")
    @GET
    @Transactional
    public void init() {
        Person person = new Person();
        person.name = "Yoda";
        person.birth = LocalDate.now().minusYears(300);
        person.persist();
    }

}
