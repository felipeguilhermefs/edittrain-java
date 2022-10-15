package eu.qwan.editrain.boundary.rest;

import eu.qwan.editrain.core.Course;
import org.springframework.stereotype.Component;

@Component
public class RestCourseMapper {

    public Course toModel(RestCourse dto) {
        return Course.builder()
            .id(dto.getId())
            .name(dto.getName())
            .description(dto.getDescription())
            .teacher(dto.getTeacher())
            .build();
    }

    public RestCourse toDto(Course model) {
        var course = new RestCourse();
        course.setId(model.getId());
        course.setName(model.getName());
        course.setDescription(model.getDescription());
        course.setTeacher(model.getTeacher());
        return course;
    }
}
