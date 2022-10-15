package eu.qwan.editrain.boundary.rest;

import static java.util.stream.Collectors.toList;

import eu.qwan.editrain.core.Course;
import eu.qwan.editrain.model.EdiTrainException;
import eu.qwan.editrain.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getCourses() {
        return ResponseEntity.ok(courseService.findAll().stream().map(this::toDto).collect(toList()));
    }

    @PostMapping(value="/courses", consumes="application/json", produces="application/json")
    public ResponseEntity<Optional<CourseDto>> createCourse(@RequestBody @Valid CourseDto body) {
        var course= courseService.create(toModel(body)).map(this::toDto);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @PutMapping(value="/courses", consumes="application/json", produces="application/json")
    public ResponseEntity<Optional<CourseDto>> updateCourse(@RequestBody @Valid CourseDto body) {
        if (body.getId() == null || body.getId().isBlank()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        courseService.update(toModel(body));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = EdiTrainException.class)
    public ResponseEntity<ErrorResponse> handleEdiTrainExceptions(EdiTrainException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private CourseDto toDto(Course model) {
        var course = new CourseDto();
        course.setId(model.getId());
        course.setName(model.getName());
        course.setDescription(model.getDescription());
        course.setTeacher(model.getTeacher());
        return course;
    }

    public Course toModel(CourseDto dto) {
        var course = new Course();
        course.setId(dto.getId());
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setTeacher(dto.getTeacher());
        return course;
    }
}
