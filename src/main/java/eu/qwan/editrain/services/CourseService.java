package eu.qwan.editrain.services;

import eu.qwan.editrain.model.Course;
import eu.qwan.editrain.repositories.CourseRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> createCourse(Course course) {
        course.setId(UUID.randomUUID().toString());
        try {
            courseRepository.save(course);
        } catch (ConstraintViolationException nonUniqueName) {
            return Optional.empty();
        }
        return Optional.of(course);
    }
}
