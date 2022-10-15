package eu.qwan.editrain.boundary.rest;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseDto {
    private String id;
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String teacher;
}
