package pl.piomin.services.quarkus.person.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import pl.piomin.services.quarkus.person.model.Person;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    public List<Person> findByName(String name) {
        return find("name", name).list();
    }

    public List<Person> findByAgeGreaterThan(int age) {
        return find("age > ?1", age).list();
    }

}
