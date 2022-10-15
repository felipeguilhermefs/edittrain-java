package eu.qwan.editrain.controllers;

import static java.util.stream.Collectors.toList;

import eu.qwan.editrain.boundary.JPACourse;
import eu.qwan.editrain.core.Course;
import eu.qwan.editrain.model.EdiTrainException;
import eu.qwan.editrain.services.CourseService;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

class ErrorResponse {
    public final String error;

    public ErrorResponse(String error) {
        this.error = error;
    }
}

@RestController
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @PostMapping(value="/courses", consumes="application/json", produces="application/json")
    public ResponseEntity<Optional<Course>> createCourse(@RequestBody @Valid Course body) {
        var course= courseService.create(body.toJPA())
            .map(Course::from);
        System.out.println(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @PutMapping(value="/courses", consumes="application/json", produces="application/json")
    public ResponseEntity<Optional<Course>> updateCourse(@RequestBody @Valid Course body) {
        if (body.getId() == null || body.getId().isBlank()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        courseService.update(body.toJPA());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = EdiTrainException.class)
    public ResponseEntity<ErrorResponse> handleEdiTrainExceptions(EdiTrainException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
