package eu.qwan.editrain.boundary.jpa;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "courses")
public class JPACourse {
    @Id
    private String id;
    private String name;
    private String description;
    private String teacher;
}
