package pl.piomin.services.quarkus.person.resource;

import org.jboss.logging.Logger;
import pl.piomin.services.quarkus.person.model.Person;
import pl.piomin.services.quarkus.person.repository.PersonRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/persons")
public class PersonResource {

    private PersonRepository repository;
    private Logger logger;

    public PersonResource(PersonRepository repository, Logger logger) {
        this.repository = repository;
        this.logger = logger;
    }

    @GET
    public List<Person> findAll() {
        logger.info("IN -> findAll");
        return repository.findAll()
                .list();
    }

    @GET
    @Path("/name/{name}")
    public List<Person> findByName(@PathParam("name") String name) {
        logger.infof("IN -> findByName(%s)", name);
        return repository.findByName(name);
    }

    @GET
    @Path("/{id}")
    public Person findById(@PathParam("id") Long id) {
        logger.infof("IN -> findById(%d)", id);
        return repository.findById(id);
    }
}