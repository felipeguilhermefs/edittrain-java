package eu.qwan.editrain.boundary.rest;

import eu.qwan.editrain.core.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseDtoMapper {

    public Course toModel(CourseDto dto) {
        var course = new Course();
        course.setId(dto.getId());
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setTeacher(dto.getTeacher());
        return course;
    }

    public CourseDto toDto(Course model) {
        var course = new CourseDto();
        course.setId(model.getId());
        course.setName(model.getName());
        course.setDescription(model.getDescription());
        course.setTeacher(model.getTeacher());
        return course;
    }
}
