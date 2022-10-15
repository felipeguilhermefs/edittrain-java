package eu.qwan.editrain.services;

import eu.qwan.editrain.core.Course;
import eu.qwan.editrain.core.CourseCatalog;
import eu.qwan.editrain.model.EdiTrainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CourseService {
    private final Logger logger = LoggerFactory.getLogger(CourseService.class);

    private final CourseCatalog courseCatalog;

    public CourseService(CourseCatalog courseCatalog) {
        this.courseCatalog = courseCatalog;
    }

    public List<Course> findAll() {
        return courseCatalog.findAll();
    }

    public Optional<Course> create(Course course) {
        course.setId(UUID.randomUUID().toString());
        try {
            courseCatalog.save(course);
        } catch (Exception probablyNonUniqueName) {
            logger.error("Probably non unique name for new course", probablyNonUniqueName);
            return Optional.empty();
        }
        return Optional.of(course);
    }

    public void update(Course course) {
        courseCatalog.findById(course.getId()).ifPresentOrElse(original -> {
            original.setName(course.getName());
            original.setDescription(course.getDescription());
            try {
                courseCatalog.save(original);
            } catch (Exception probablyNonUniqueName) {
                logger.error("Probably non unique name for new course", probablyNonUniqueName);
                throw new EdiTrainException("Error updating course, name should be unique", probablyNonUniqueName);
            }}, () -> {
            throw new EdiTrainException("Course id " + course.getId() + " does not exist");
        });
    }
}
