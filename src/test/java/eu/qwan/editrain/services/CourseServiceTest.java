package eu.qwan.editrain.services;

import eu.qwan.editrain.core.Courses;
import eu.qwan.editrain.model.Course;
import eu.qwan.editrain.model.EdiTrainException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.*;

public class CourseServiceTest {
    private final Courses courses = mock(Courses.class);
    private final CourseService courseService = new CourseService(courses);

    @Nested
    class WhenCreatingACourse {
        @Test
        public void savesItInTheRepository() {
            var createdCourse = courseService.create(new Course("", "name", "description", "marc@edutrain.eu")).get();
            verify(courses).save(createdCourse);
            assertThat(createdCourse.getId(), is(not("")));
        }

        @Test
        public void failsWhenNewCourseNameIsNotUnique() {
            when(courses.save(any())).thenThrow(new ConstraintViolationException("Error", null, "name"));
            var createdCourse = courseService.create(new Course("", "name", "description", "marc@edutrain.eu"));
            assertThat(createdCourse, is(Optional.empty()));
        }
    }
    @Nested
    class WhenGettingCourses {
        @Test
        public void returnsAllCoursesFromTheRepository() {
            Course course = Course.aValidCourse().build();
            when(courses.findAll()).thenReturn(List.of(course));
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
            when(courses.findById(course.getId())).thenReturn(Optional.of(course));
            courseService.update(updated);
            verify(courses).save(updated);
        }

        @Test
        public void leavesTeacherUnchanged() {
            var original = Course.aValidCourse().teacher("original@edutrain.eu").build();
            var updated = Course.aValidCourse().description("updated").teacher("updated@editrain.eu");
            when(courses.findById(original.getId())).thenReturn(Optional.of(original));
            courseService.update(updated.build());
            verify(courses).save(Course.aValidCourse().description("updated").teacher("original@edutrain.eu").build());
        }

        @Test
        public void failsWhenTheCourseDoesNotExist() {
            var course = Course.aValidCourse().build();
            when(courses.findById(course.getId())).thenReturn(Optional.empty());
            Assertions.assertThrows(EdiTrainException.class, () -> courseService.update(course));
            verify(courses, never()).save(any());
        }

        @Test
        public void failsWhenNewCourseNameIsNotUnique() {
            var original = Course.aValidCourse().build();
            var updated = Course.aValidCourse().name("updated").build();
            when(courses.findById(original.getId())).thenReturn(Optional.of(original));
            when(courses.save(any())).thenThrow(new ConstraintViolationException("Error", null, "name"));
            Assertions.assertThrows(EdiTrainException.class, () -> courseService.update(updated));
        }
    }
}
