package pl.piomin.services.quarkus.person.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;
import org.jboss.logging.Logger;
import org.jboss.logging.MDC;
import pl.piomin.services.quarkus.person.model.Person;
import pl.piomin.services.quarkus.person.repository.PersonRepository;

import java.util.List;

@Path("/persons")
public class PersonResource {

    private PersonRepository repository;
    private Logger logger;

    public PersonResource(PersonRepository repository, Logger logger) {
        this.repository = repository;
        this.logger = logger;
    }

    @POST
    @Transactional
    public Person add(Person person) {
        repository.persist(person);
        MDC.put("personId", person.id);
        logger.infof("IN -> add(%s)", person);
        return person;
    }

    @GET
    @APIResponseSchema(Person.class)
    public List<Person> findAll() {
        logger.info("IN -> findAll");
        return repository.findAll()
                .list();
    }

//    @GET
//    @Path("/v2")
//    public List<Person> findAllV2() {
//        logger.info("IN -> findAllV2");
//        return repository.findAll()
//                .list();
//    }

    @GET
    @Path("/name/{name}")
    public List<Person> findByName(@PathParam("name") String name) {
        logger.infof("IN -> findByName(%s)", name);
        return repository.findByName(name);
    }

    @GET
    @Path("/{id}")
    public Person findById(@PathParam("id") Long id) {
        MDC.put("personId", id);
        logger.infof("IN -> findById(%d)", id);
        return repository.findById(id);
    }
}