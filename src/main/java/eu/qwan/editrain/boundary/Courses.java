package eu.qwan.editrain.boundary;

import eu.qwan.editrain.model.Course;
import java.util.List;

public interface Courses {

    List<Course> findAll();

    Course save(Course course);
}
