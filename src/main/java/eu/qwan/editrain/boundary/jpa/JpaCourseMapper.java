package eu.qwan.editrain.boundary.jpa;

import eu.qwan.editrain.core.Course;
import org.springframework.stereotype.Component;

@Component
public class JpaCourseMapper {

    public Course toModel(JpaCourse entity) {
        var course = new Course();
        course.setId(entity.getId());
        course.setName(entity.getName());
        course.setDescription(entity.getDescription());
        course.setTeacher(entity.getTeacher());
        return course;
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
