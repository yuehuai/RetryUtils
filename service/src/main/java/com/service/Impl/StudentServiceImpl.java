package com.service.Impl;

import com.entity.student;
import com.mapper.StudentMapper;
import com.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Log4j2
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<student> selectAll() {
        return studentMapper.selectAllStudents();
    }
    @Override
    public List<String> retryTest(){


        log.info("方法执行");
//       System.out.println(1 / 0);
        List<String> list = new ArrayList<>();
        list.add("1");
        return list;
    }
    @Override
    public List<String> retryTestnoneE(){

        System.out.println();
        log.info("方法执行");
      Integer i=null;
        System.out.println(i+1);
        List<String> list = new ArrayList<>();
        list.add("1");
        return list;
    }

    @Override
    public void countTest1() {
        log.info("方法执行");
        System.out.println(1 / 0);
    }

    @Override
    public void countTest2() {
        log.info("方法执行");
        Integer i=null;
        System.out.println(i+1);
    }
}
