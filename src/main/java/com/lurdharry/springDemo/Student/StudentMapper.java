package com.lurdharry.springDemo.Student;

import com.lurdharry.springDemo.School.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(student.getFirstName(), student.getLastName(), student.getEmail());
    }

    public  Student toStudent(StudentDto studentDto){
        if (studentDto == null) {
            throw new NullPointerException("The studentDto is null");
        }
        var student = new Student();
        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setEmail(studentDto.email());

        var school = new School();
        school.setId(studentDto.schoolId());

        student.setSchool(school);
        return student;
    }
}
