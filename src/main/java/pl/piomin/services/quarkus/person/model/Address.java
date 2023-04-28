package pl.piomin.services.quarkus.person.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    public String street;
    public String city;
    public int flatNo;
    public int buildingNo;
}
