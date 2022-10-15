package eu.qwan.editrain.core;

import eu.qwan.editrain.boundary.JPACourse;
import java.util.List;
import java.util.Optional;

public interface Courses {

    List<Course> findAll();

    Optional<JPACourse> findById(String id);

    Course save(Course course);
}
