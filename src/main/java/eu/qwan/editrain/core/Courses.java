package eu.qwan.editrain.core;

import java.util.List;
import java.util.Optional;

public interface Courses {

    List<Course> findAll();

    Optional<Course> findById(String id);

    Course save(Course course);
}
