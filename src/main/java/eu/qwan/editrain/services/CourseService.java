package eu.qwan.editrain.services;

import eu.qwan.editrain.model.Course;
import eu.qwan.editrain.repositories.CourseRepository;
import org.hibernate.id.GUIDGenerator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CourseService {
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course createCourse(String name, String description) {
        var course = new Course(UUID.randomUUID().toString(), name, description);
        courseRepository.save(course);
        return course;
    }
}
