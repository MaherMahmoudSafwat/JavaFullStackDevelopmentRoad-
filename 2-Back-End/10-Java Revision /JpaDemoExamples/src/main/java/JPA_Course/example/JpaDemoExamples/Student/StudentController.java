package JPA_Course.example.JpaDemoExamples.Student;

import JPA_Course.example.JpaDemoExamples.Dtos.StudentDto;
import JPA_Course.example.JpaDemoExamples.Examples.Student;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController
{
    private StudentService studentService;
    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }
    @GetMapping
    public List<StudentDto>GetAllStudentsData()
    {
        return studentService.GetAllStudentsData();
    }
    @PostMapping
    public void SaveAllStudentsData(@RequestBody @Valid Student student)
    {
        studentService.SaveAllStudentsData(student);
    }
    /**
     * YOUR ORIGINAL CODE EXPLAINED LINE BY LINE:
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> ErrorsThatHasBeenFound(MethodArgumentNotValidException exp) {
        /*
        LINE BY LINE EXPLANATION:

        @ExceptionHandler(MethodArgumentNotValidException.class)
        - This catches validation exceptions thrown by @Valid annotation
        - MethodArgumentNotValidException = thrown when @Valid fails on request body
        - Automatically called when validation fails in controller methods

        public ResponseEntity<?> ErrorsThatHasBeenFound
        - ResponseEntity<?> = generic return type (can return anything)
        - Method name should follow camelCase convention: errorsFound or handleValidationErrors
        - Better to use specific return type: ResponseEntity<Map<String, String>>

        MethodArgumentNotValidException exp
        - 'exp' parameter contains all validation error details
        - Contains BindingResult with field errors, global errors, etc.
        */

        var Errors = new HashMap<String,String>();
        /*
        var Errors = new HashMap<String,String>();
        - 'var' keyword - Java 10+ type inference
        - Variable name should be camelCase: errors (not Errors)
        - Creating map to store field name -> error message pairs
        */

        exp.getBindingResult().getAllErrors().
                forEach(errors-> {
        /*
        exp.getBindingResult().getAllErrors()
        - getBindingResult(): Gets validation result details
        - getAllErrors(): Gets list of all validation errors (ObjectError objects)
        - forEach(): Iterates through each error
        - Lambda parameter 'errors' should be singular 'error'
        */

                    var Fields = ((FieldError) errors).getField();
                    var Messages = errors.getDefaultMessage();
                    Errors.put(Fields,Messages);
        /*
        var Fields = ((FieldError) errors).getField();
        - Cast ObjectError to FieldError to get field name
        - POTENTIAL BUG: Not all errors are FieldErrors (some are global errors)
        - Variable should be camelCase: fields -> field

        var Messages = errors.getDefaultMessage();
        - Gets the error message (from validation annotation)
        - Should be singular: message (not Messages)

        Errors.put(Fields,Messages);
        - Adds field name and message to map
        - POTENTIAL BUG: If same field has multiple errors, only last one is kept
        */
                });
        return new ResponseEntity<>(Errors, HttpStatus.BAD_REQUEST);
        /*
        return new ResponseEntity<>(Errors, HttpStatus.BAD_REQUEST);
        - Returns map with 400 Bad Request status
        - Good choice of HTTP status for validation errors
        */
    }
}
