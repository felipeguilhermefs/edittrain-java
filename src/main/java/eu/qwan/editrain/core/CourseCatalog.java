package eu.qwan.editrain.core;

import java.util.List;
import java.util.Optional;

public interface CourseCatalog {

    List<Course> findAll();

    Optional<Course> findById(String id);

    Course save(Course course);
}
