package eu.qwan.editrain.boundary.jpa;

import eu.qwan.editrain.core.Course;
import org.springframework.stereotype.Component;

@Component
public class JpaCourseMapper {

    public Course toModel(JpaCourse entity) {
        return Course.builder()
            .id(entity.getId())
            .name(entity.getName())
            .description(entity.getDescription())
            .teacher(entity.getTeacher())
            .build();
    }

    public JpaCourse toEntity(Course model) {
        var course = new JpaCourse();
        course.setId(model.getId());
        course.setName(model.getName());
        course.setDescription(model.getDescription());
        course.setTeacher(model.getTeacher());
        return course;
    }
}
