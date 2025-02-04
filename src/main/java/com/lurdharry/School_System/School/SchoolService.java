package com.lurdharry.School_System.School;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    SchoolDto create(
            SchoolDto schoolDto
    ){
        var school = schoolMapper.toSchool(schoolDto);
        schoolRepository.save(school);
        return schoolDto;
    }

    List<SchoolDto> findAll (){
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());
    }
}
