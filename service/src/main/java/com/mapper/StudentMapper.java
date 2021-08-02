package com.mapper;

import com.entity.student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    //查询表的所有数据
    public List<student> selectAllStudents();
}
