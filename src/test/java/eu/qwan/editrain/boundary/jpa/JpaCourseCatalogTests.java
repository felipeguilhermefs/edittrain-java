package eu.qwan.editrain.boundary.jpa;

import eu.qwan.editrain.core.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class JpaCourseCatalogTests {

    @Autowired
    JpaCourseCatalog courseCatalog;

    @Test
    public void isEmptyInitially() {
        assertThat(courseCatalog.findAll(), is(empty()));
    }

    @Test
    public void containsSavedCourses() {
        var course = new Course(UUID.randomUUID().toString(), "name", "description", "john@edutrain.eu");
        courseCatalog.save(course);
        assertThat(courseCatalog.findAll(), is(List.of(course)));
    }
}
