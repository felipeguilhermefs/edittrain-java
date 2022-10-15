package eu.qwan.editrain.boundary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class JPACoursesTests {

    @Autowired
    JPACourses courses;

    @Test
    public void isEmptyInitially() {
        assertThat(courses.findAll(), is(empty()));
    }

    @Test
    public void containsSavedCourses() {
        Course course = new Course(UUID.randomUUID().toString(), "name", "description", "john@edutrain.eu");
        courses.save(course);
        assertThat(courses.findAll(), is(List.of(course)));
    }
}
