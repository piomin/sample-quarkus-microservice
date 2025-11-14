package pl.piomin.services.quarkus.person.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;

@Entity
@Schema(properties = {
        @SchemaProperty(name = "id", example = "1", title = "Main id of person"),
        @SchemaProperty(name = "name", example = "John Wick", title = "Name of person"),
        @SchemaProperty(name = "age", example = "18", title = "Age of person"),
        @SchemaProperty(name = "gender", enumeration = {"MALE", "FEMALE"}, title = "Gender of person"),
        @SchemaProperty(name = "externalId", example = "100", title = "External identificator of person")
    }
)
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
//    @Nullable
    public Address address;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", externalId=" + externalId +
                '}';
    }
}
