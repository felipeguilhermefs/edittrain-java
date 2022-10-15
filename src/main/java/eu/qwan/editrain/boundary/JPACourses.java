package eu.qwan.editrain.boundary;

import eu.qwan.editrain.model.Course;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class JPACourses implements Courses {

    private final CourseRepository repository;

    public JPACourses(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Course> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Course> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Course save(Course course) {
        return repository.save(course);
    }
}
