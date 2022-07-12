package co.com.sofka.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PetDocument {
    @Id
    private String id;
    private String name;
    private String breed;
}
