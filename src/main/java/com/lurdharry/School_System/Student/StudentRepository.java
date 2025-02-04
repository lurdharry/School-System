package com.lurdharry.School_System.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

     List<Student> findAllByFirstNameContaining(String firstName);
}
