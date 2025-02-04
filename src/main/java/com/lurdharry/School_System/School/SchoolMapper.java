package com.lurdharry.School_System.School;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {

     School  toSchool(SchoolDto schoolDto) {
        return  new School(schoolDto.name());
    }

     SchoolDto toSchoolDto (School school){
        return  new SchoolDto(school.getName());
    }
}
