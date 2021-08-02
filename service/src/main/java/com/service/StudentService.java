package com.service;

import com.entity.student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<student> selectAll();
    List<String> retryTest() ;
    List<String> retryTestnoneE();
    void countTest1() ;
    void countTest2() ;
}
