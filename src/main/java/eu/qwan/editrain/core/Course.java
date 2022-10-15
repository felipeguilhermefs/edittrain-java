package eu.qwan.editrain.core;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Course {
    private String id;
    private String name;
    private String description;
    private String teacher;

    public static CourseBuilder aValidCourse() {
        return builder().id("some-id").name("Design Fundamentals").description("Some description").teacher("teacher@editrain.eu");
    }
}
