package eu.qwan.editrain.core;

import eu.qwan.editrain.boundary.JPACourse;
import java.util.List;
import java.util.Optional;

public interface Courses {

    List<JPACourse> findAll();

    Optional<JPACourse> findById(String id);

    JPACourse save(JPACourse course);
}
