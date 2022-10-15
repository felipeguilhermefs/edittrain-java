package eu.qwan.editrain.boundary.jpa;

import static java.util.stream.Collectors.toList;

import eu.qwan.editrain.core.Course;
import eu.qwan.editrain.core.Courses;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class JpaCourses implements Courses {

    private final JpaCourseRepository repository;
    private final JpaCourseMapper mapper;

    public JpaCourses(
        JpaCourseRepository repository,
        JpaCourseMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Course> findAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toModel)
            .collect(toList());
    }

    @Override
    public Optional<Course> findById(String id) {
        return repository.findById(id).map(mapper::toModel);
    }

    @Override
    public Course save(Course course) {
        var entity = mapper.toEntity(course);
        var saved = repository.save(entity);
        return mapper.toModel(saved);
    }
}
