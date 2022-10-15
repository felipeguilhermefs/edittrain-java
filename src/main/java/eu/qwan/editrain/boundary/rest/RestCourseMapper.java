package eu.qwan.editrain.boundary.rest;

import eu.qwan.editrain.core.Course;
import org.springframework.stereotype.Component;

@Component
public class RestCourseMapper {

    public Course toModel(RestCourse dto) {
        return Course.builder()
            .id(dto.id())
            .name(dto.name())
            .description(dto.description())
            .teacher(dto.teacher())
            .build();
    }

    public RestCourse toDto(Course model) {
        return new RestCourse(
            model.getId(),
            model.getName(),
            model.getDescription(),
            model.getTeacher()
        );
    }
}
