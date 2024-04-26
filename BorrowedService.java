package com.example.service;

import com.example.pojo.Borrowed;
import com.example.util.PageInfo;

import java.util.List;

public interface BorrowedService {
    List<Borrowed> selectAll(Integer userId);

    void deleteById(Integer id);

    Borrowed selectById(Integer id);


    PageInfo selectByPage(Integer pageNo, Integer pageSize);

    void deleteAll(Integer[] ids);

    void add(Borrowed borrowed);
}
