package eu.qwan.editrain.services;

import eu.qwan.editrain.model.Course;
import eu.qwan.editrain.repositories.CourseRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CourseServiceTest {
    @MockBean
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;

    @Nested
    class WhenCreatingACourse {
        @Test
        public void savesItInTheRepository() {
            var createdCourse = courseService.create(new Course("", "name", "description", "marc@edutrain.eu")).get();
            verify(courseRepository).save(createdCourse);
            assertThat(createdCourse.getId(), is(not("")));
        }

        @Test
        public void failsWhenNewCourseNameIsNotUnique() {
            when(courseRepository.save(any())).thenThrow(new ConstraintViolationException("Error", null, "name"));
            var createdCourse = courseService.create(new Course("", "name", "description", "marc@edutrain.eu"));
            assertThat(createdCourse, is(Optional.empty()));
        }
    }
    @Nested
    class WhenGettingCourses {
        @Test
        public void returnsAllCoursesFromTheRepository() {
            Course course = Course.aValidCourse().build();
            when(courseRepository.findAll()).thenReturn(List.of(course));
            var courses = courseService.findAll();
            assertThat(courses, is(List.of(course)));
        }
    }

    @Nested
    class WhenUpdatingACourse {
        @Test
        public void savesChangesInTheRepository() {
            var course = Course.aValidCourse().build();
            var updated = Course.aValidCourse().name("new name").description("updated").build();
            when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
            courseService.update(updated);
            verify(courseRepository).save(updated);
        }

        @Test
        public void leavesTeacherUnchanged() {
            var original = Course.aValidCourse().teacher("original@edutrain.eu").build();
            var updated = Course.aValidCourse().description("updated").teacher("updated@editrain.eu");
            when(courseRepository.findById(original.getId())).thenReturn(Optional.of(original));
            courseService.update(updated.build());
            verify(courseRepository).save(Course.aValidCourse().description("updated").teacher("original@edutrain.eu").build());
        }

        @Test
        public void failsWhenTheCourseDoesNotExist() {
            var course = Course.aValidCourse().build();
            when(courseRepository.findById(course.getId())).thenReturn(Optional.empty());
            Assertions.assertThrows(RuntimeException.class, () -> courseService.update(course));
            verify(courseRepository, never()).save(any());
        }

        @Test
        public void failsWhenNewCourseNameIsNotUnique() {
            var original = Course.aValidCourse().build();
            var updated = Course.aValidCourse().name("updated").build();
            when(courseRepository.findById(original.getId())).thenReturn(Optional.of(original));
            when(courseRepository.save(any())).thenThrow(new ConstraintViolationException("Error", null, "name"));
            courseService.update(updated);
        }
    }
}
