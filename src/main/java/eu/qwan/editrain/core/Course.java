package eu.qwan.editrain.core;

import eu.qwan.editrain.boundary.JPACourse;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Course {
    private String id;
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String teacher;

    public static Course from(JPACourse jpa) {
        var course = new Course();
        course.setId(jpa.getId());
        course.setName(jpa.getName());
        course.setDescription(jpa.getDescription());
        course.setTeacher(jpa.getTeacher());
        return course;
    }

    public JPACourse toJPA() {
        var course = new JPACourse();
        course.setId(id);
        course.setName(name);
        course.setDescription(description);
        course.setTeacher(teacher);
        return course;
    }
}
