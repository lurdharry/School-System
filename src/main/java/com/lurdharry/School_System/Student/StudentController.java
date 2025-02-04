package com.lurdharry.School_System.Student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

  private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto addStudent(
          @Valid @RequestBody StudentDto studentDto
    ){
         return studentService.saveStudent(studentDto);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudents(){
        return studentService.findAllStudents();
    }

    @GetMapping("/students/{student-id}")
    public StudentResponseDto findStudentById(
            @PathVariable("student-id") Integer id
      ){
        return studentService.findStudentById(id);
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> findStudentsByName(
            @PathVariable("student-name") String name
      ){
        return studentService.findStudentsByName(name);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable("student-id") Integer id
    ) {
        studentService.deleteStudentById(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ){
      var errors = new HashMap<String,String>();
      exception.getBindingResult().getAllErrors()
              .forEach(error->{
                var fieldName = ((FieldError) error).getField();
                var errorMessage = error.getDefaultMessage();
                errors.put(fieldName,errorMessage);
              });
      return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
