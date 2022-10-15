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
    private final CourseDtoMapper mapper;

    public CourseController(
        CourseService courseService,
        CourseDtoMapper mapper
    ) {
        this.courseService = courseService;
        this.mapper = mapper;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getCourses() {
        return ResponseEntity.ok(
            courseService.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(toList())
        );
    }

    @PostMapping(value="/courses", consumes="application/json", produces="application/json")
    public ResponseEntity<Optional<CourseDto>> createCourse(@RequestBody @Valid CourseDto body) {
        var course = mapper.toModel(body);
        return new ResponseEntity<>(
            courseService.create(course).map(mapper::toDto),
            HttpStatus.CREATED
        );
    }

    @PutMapping(value="/courses", consumes="application/json", produces="application/json")
    public ResponseEntity<Optional<CourseDto>> updateCourse(@RequestBody @Valid CourseDto body) {
        if (body.getId() == null || body.getId().isBlank()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var course = mapper.toModel(body);
        courseService.update(course);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = EdiTrainException.class)
    public ResponseEntity<ErrorResponse> handleEdiTrainExceptions(EdiTrainException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
