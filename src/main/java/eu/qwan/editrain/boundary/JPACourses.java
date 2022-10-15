package eu.qwan.editrain.boundary;

import static java.util.stream.Collectors.toList;

import eu.qwan.editrain.core.Course;
import eu.qwan.editrain.core.Courses;
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
        return repository.findAll()
            .stream()
            .map(Course::from)
            .collect(toList());
    }

    @Override
    public Optional<JPACourse> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Course save(Course course) {
        return Course.from(repository.save(course.toJPA()));
    }
}
