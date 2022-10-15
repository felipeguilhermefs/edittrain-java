package eu.qwan.editrain.boundary;

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
    public List<JPACourse> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<JPACourse> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public JPACourse save(JPACourse course) {
        return repository.save(course);
    }
}
