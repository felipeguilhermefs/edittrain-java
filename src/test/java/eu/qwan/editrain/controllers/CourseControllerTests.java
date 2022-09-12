package eu.qwan.editrain.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.qwan.editrain.model.Course;
import eu.qwan.editrain.services.CourseService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static eu.qwan.editrain.controllers.MockMvcJsonRequests.jsonGet;
import static eu.qwan.editrain.controllers.MockMvcJsonRequests.jsonPost;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTests {
    @MockBean
    private CourseService courseService;

    @Autowired
    private CourseController courseController;

    @Autowired
    private MockMvc mockMvc;

    @Nested
    public class GettingAllCourses {
        @Test
        public void getCourses_ReturnsAnEmptyListWhenNoCoursesArePresent() throws Exception {
            when(courseService.findAll()).thenReturn(emptyList());
            mockMvc.perform(jsonGet("/courses")).andExpect(status().isOk());
        }

        @Test
        public void getCourses_ReturnsAListOfCoursesWhenCoursesExistInRepository() throws Exception {
            Course course1 = Course.builder().id("1").name("Course1").description("someDescription1").build();
            Course course2 = Course.builder().id("2").name("Course2").description("someDescription2").build();
            when(courseService.findAll()).thenReturn(List.of(course1, course2));
            mockMvc.perform(jsonGet("/courses"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id", is("1")))
                    .andExpect(jsonPath("$[0].name", is("Course1")))
                    .andExpect(jsonPath("$[0].description", is("someDescription1")))
                    .andExpect(jsonPath("$[1].id", is("2")))
                    .andExpect(jsonPath("$[1].name", is("Course2")))
                    .andExpect(jsonPath("$[1].description", is("someDescription2")));
        }
    }
    @Nested
    public class PostingACourse {
        @Test
        public void savesItInTheDatabase() throws Exception {
            Course theCourse = Course.builder().id("someId").name("courseName").description("someDescription").build();
            when(courseService.createCourse(theCourse)).thenReturn(theCourse);
            mockMvc.perform(jsonPost("/courses", toJson(theCourse)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(theCourse.getId())));
        }

        @Test
        public void returns400WhenDataIsNotValid() throws Exception {
            Course theCourse = Course.builder().id("someId").name("").description("someDescription").build();
            mockMvc.perform(jsonPost("/courses", toJson(theCourse)))
                    .andExpect(status().is4xxClientError());
        }
    }

    private <T> String toJson(T body) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(body);
    }
}
