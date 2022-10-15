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
public class JpaCoursesTests {

    @Autowired
    JpaCourses courses;

    @Test
    public void isEmptyInitially() {
        assertThat(courses.findAll(), is(empty()));
    }

    @Test
    public void containsSavedCourses() {
        var course = Course.builder()
            .id(UUID.randomUUID().toString())
            .name("name")
            .description("description")
            .teacher("john@edutrain.eu")
            .build();
        courses.save(course);
        assertThat(courses.findAll(), is(List.of(course)));
    }
}
