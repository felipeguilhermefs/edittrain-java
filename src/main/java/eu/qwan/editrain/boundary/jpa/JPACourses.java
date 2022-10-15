package eu.qwan.editrain.boundary.jpa;

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
            .map(this::toModel)
            .collect(toList());
    }

    @Override
    public Optional<Course> findById(String id) {
        return repository.findById(id).map(this::toModel);
    }

    @Override
    public Course save(Course course) {
        return toModel(repository.save(toJPA(course)));
    }

    private Course toModel(JPACourse jpa) {
        var course = new Course();
        course.setId(jpa.getId());
        course.setName(jpa.getName());
        course.setDescription(jpa.getDescription());
        course.setTeacher(jpa.getTeacher());
        return course;
    }

    private JPACourse toJPA(Course model) {
        var course = new JPACourse();
        course.setId(model.getId());
        course.setName(model.getName());
        course.setDescription(model.getDescription());
        course.setTeacher(model.getTeacher());
        return course;
    }
}
