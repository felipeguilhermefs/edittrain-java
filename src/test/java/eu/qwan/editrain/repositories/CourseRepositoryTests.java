package eu.qwan.editrain.repositories;

import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import eu.qwan.editrain.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class CourseRepositoryTests {
    @Autowired
    CourseRepository courseRepository;
    @Test
    public void isEmptyInitially() {
        assertThat(courseRepository.findAll(), is(empty()));
    }
    @Test
    public void containsSavedCourses() {
        Course course = new Course(UUID.randomUUID().toString(), "name", "description");
        courseRepository.save(course);
        assertThat(courseRepository.findAll(), is(List.of(course)));
    }
}
