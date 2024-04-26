package com.example.service;

import com.example.pojo.Book;
import com.example.util.PageInfo;

import java.util.List;

public interface BookService {
    List<Book> selectAll();

    void deleteById(Integer id);

    void add(Book book);

    Book selectById(Integer id);

    void update(Book book);

    PageInfo selectByPage(Integer pageNo, Integer pageSize);

    void deleteAll(Integer[] ids);
}
