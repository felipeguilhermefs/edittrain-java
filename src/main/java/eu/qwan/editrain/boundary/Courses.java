package eu.qwan.editrain.boundary;

import eu.qwan.editrain.model.Course;
import java.util.List;
import java.util.Optional;

public interface Courses {

    List<Course> findAll();

    Optional<Course> findById(String id);

    Course save(Course course);
}
