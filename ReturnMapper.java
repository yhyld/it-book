package com.example.mapper;

import com.example.pojo.Borrowed;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.example.pojo.Borrowed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BorrowedMapper {
    List<Borrowed> selectAll(Integer userId);
    void deleteById(Integer id);
    Borrowed selectById(Integer id);
    List<Borrowed> selectByPage(@Param("offset") int offset,@Param("pageSize") Integer pageSize);
    int selectTotalCount();
    void deleteAll(Integer[] ids);

    void add(Borrowed borrowed);
}
