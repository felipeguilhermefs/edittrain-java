package eu.qwan.editrain.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class Catalog {
    private final Logger logger = LoggerFactory.getLogger(Catalog.class);

    private final Courses courses;

    public Catalog(Courses courses) {
        this.courses = courses;
    }

    public List<Course> findAllCourses() {
        return courses.findAll();
    }

    public Optional<Course> addCourse(Course course) {
        course.setId(UUID.randomUUID().toString());
        try {
            courses.save(course);
        } catch (Exception probablyNonUniqueName) {
            logger.error("Probably non unique name for new course", probablyNonUniqueName);
            return Optional.empty();
        }
        return Optional.of(course);
    }

    public void updateCourse(Course course) {
        courses.findById(course.getId()).ifPresentOrElse(original -> {
            original.setName(course.getName());
            original.setDescription(course.getDescription());
            try {
                courses.save(original);
            } catch (Exception probablyNonUniqueName) {
                logger.error("Probably non unique name for new course", probablyNonUniqueName);
                throw new EdiTrainException("Error updating course, name should be unique", probablyNonUniqueName);
            }}, () -> {
            throw new EdiTrainException("Course id " + course.getId() + " does not exist");
        });
    }
}
