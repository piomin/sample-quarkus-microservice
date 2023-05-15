package pl.piomin.services.quarkus.person.model;

import jakarta.persistence.Embeddable;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;

@Embeddable
@Schema(properties = {
        @SchemaProperty(name = "street", example = "Test Street", title = "Street"),
        @SchemaProperty(name = "city", example = "Warsaw", title = "Name of city"),
        @SchemaProperty(name = "flatNo", example = "18", title = "Number of flat"),
        @SchemaProperty(name = "buildingNo", example = "100", title = "Number of building")
}
)
public class Address {
    public String street;
    public String city;
    public int flatNo;
    public int buildingNo;
}
