package eu.qwan.editrain.controllers;

import eu.qwan.editrain.model.Course;
import eu.qwan.editrain.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {
    private CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @PostMapping(value="/courses", consumes="application/json", produces="application/json")
    public ResponseEntity<Optional<Course>> createCourse(@RequestBody @Valid Course body) {
        var course= courseService.createCourse(body);
        System.out.println(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }
}
