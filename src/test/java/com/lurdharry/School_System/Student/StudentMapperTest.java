package com.lurdharry.School_System.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class StudentMapperTest {

  private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent(){
        StudentDto studentDto = new StudentDto(
                "John",
                "Doe",
                "john@mail.com",
                1
        );
        Student student =studentMapper.toStudent(studentDto);
        assertEquals(studentDto.firstName(),student.getFirstName());
        assertEquals(studentDto.lastName(),student.getLastName());
        assertEquals(studentDto.email(),student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(studentDto.schoolId(),student.getSchool().getId());
    }

    @Test
    public void  should_threw_error_when_studentDto_is_null(){
      var error = assertThrows(NullPointerException.class,()-> studentMapper.toStudent(null));
      assertEquals("The studentDto is null", error.getMessage());
    }


    @Test
    public void shouldMapStudentToStudentResponseDto(){
            Student student = new Student("John","Doe","John@mail.com",0);

            StudentResponseDto studentResponseDto = studentMapper.toStudentResponseDto(student);
            assertEquals(student.getFirstName(),studentResponseDto.firstName());
            assertEquals(student.getLastName(),studentResponseDto.lastName());
            assertEquals(student.getEmail(),studentResponseDto.email());
    }

}