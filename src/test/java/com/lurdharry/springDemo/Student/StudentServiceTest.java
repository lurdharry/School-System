package com.lurdharry.springDemo.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    //    dependencies
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_student(){
        //  Given
        StudentDto studentDto = new StudentDto(
                "John",
                "Doe",
                "john@mail.com",
                1
        );
        Student student = new Student(
                "John",
                "Doe",
                "john@mail.com",
                1
        );
        Student savedStudent = new Student(
                "John",
                "Doe",
                "john@mail.com",
                1
        );
        savedStudent.setId(1);

        // mock the calls
        when(studentMapper.toStudent(studentDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(new StudentResponseDto(
                "John","Doe","john@mail.com")
        );

        //  When
        StudentResponseDto studentResponseDto = studentService.saveStudent(studentDto);

        // Then
        assertEquals(studentDto.firstName(),studentResponseDto.firstName());
        assertEquals(studentDto.lastName(),studentResponseDto.lastName());
        assertEquals(studentDto.email(),studentResponseDto.email());

        verify(studentMapper,times(1))
                .toStudent(studentDto);
        verify(studentRepository,times(1))
                .save(student);
        verify(studentMapper,times(1))
                .toStudentResponseDto(savedStudent);
    }


    @Test
    public void should_return_all_students(){
        List<Student> students = new ArrayList<>();
        students.add(
                new Student("John","Doe","John@mail.com",0)
        );
//        Mock the calls
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class))).thenReturn(
                new StudentResponseDto(
                        "John",
                        "Doe",
                        "john@mail.com"
                )
        );

        List<StudentResponseDto> responseDtos = studentService.findAllStudents();

        assertEquals(students.size(),responseDtos.size() );

        verify(studentRepository,times(1))
                .findAll();
    }

    @Test
    public  void should_finds_students_by_Id(){

        Integer studentId = 1;
        Student student = new Student("John","Doe","john@mail.com",0);

        //  Mock the calls
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(any(Student.class))).thenReturn(
                new StudentResponseDto(
                        "John",
                        "Doe",
                        "john@mail.com"
                )
        );
        //   when
        StudentResponseDto studentResponseDto   = studentService.findStudentById(studentId);


        // Then
        assertEquals(student.getFirstName(),studentResponseDto.firstName());
        assertEquals(student.getLastName(),studentResponseDto.lastName());
        assertEquals(student.getEmail(),studentResponseDto.email());

//        verify
        verify(studentRepository,times(1))
                .findById(studentId );

    }

    @Test
    public  void should_find_all_student_by_name(){
        String studentName = "jo";
        List<Student> students = new ArrayList<>();
        students.add(
                new Student("John","Doe","John@mail.com",0)
        );
        students.add(
                new Student("Joh","Doe","John@mail.com",0)
        );


        //  Mock the calls
        when(studentRepository.findAllByFirstNameContaining(studentName)).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class))).thenReturn(
                new StudentResponseDto(
                        "John",
                        "Doe",
                        "john@mail.com"
                )
        );
        //   when
        List<StudentResponseDto> studentResponseDto   = studentService.findStudentsByName(studentName);


        // Then
        assertEquals(students.size(),studentResponseDto.size());


        //      verify
        verify(studentRepository,times(1))
                .findAllByFirstNameContaining(studentName );

    }


    @Test
    public void should_delete_student_by_id(){
        Integer studentId = 1;

        //   Mock the calls
       doNothing().when(studentRepository).deleteById(studentId);

        studentService.deleteStudentById(studentId);

        verify(studentRepository, times(1)).deleteById(studentId);
    }
}