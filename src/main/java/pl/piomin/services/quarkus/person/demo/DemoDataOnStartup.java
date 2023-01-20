package pl.piomin.services.quarkus.person.demo;

import io.quarkus.runtime.StartupEvent;
import net.datafaker.Faker;
import pl.piomin.services.quarkus.person.model.Address;
import pl.piomin.services.quarkus.person.model.Gender;
import pl.piomin.services.quarkus.person.model.Person;
import pl.piomin.services.quarkus.person.repository.PersonRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

@ApplicationScoped
public class DemoDataOnStartup {

    private final Faker faker = new Faker();
    private PersonRepository repository;

    public DemoDataOnStartup(PersonRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void init(@Observes StartupEvent event) {
        for (int i = 0; i < 20; i++) {
            repository.persist(generatePerson());
        }
    }

    private Person generatePerson() {
        final Address addr = new Address();
        addr.street = faker.address().streetName();
        addr.city = faker.address().city();
        addr.buildingNo = Integer.parseInt(faker.address().buildingNumber());
        addr.flatNo = 1;
        final Person person = new Person();
        person.age = faker.number().numberBetween(18, 70);
        person.gender = Gender.valueOf(faker.gender().binaryTypes().toUpperCase());
        person.name = faker.name().fullName();
        person.address = addr;
        person.externalId = faker.number().positive();
        return person;
    }
}
