package pl.piomin.services.quarkus.person.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Person extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public int age;
    @Enumerated(EnumType.STRING)
    public Gender gender;
    public Integer externalId;
    @Embedded
    public Address address;
}
