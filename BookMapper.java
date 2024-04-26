package com.example.mapper;

import com.example.pojo.Book;
import com.example.pojo.BookVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    //@Select("select * from book")
    List<Book> selectAll();

    void deleteById(Integer id);

    void add(Book book);

    Book selectById(Integer id);

    void update(Book book);

    List<BookVO> selectByPage(@Param("offset") int offset, @Param("pageSize") Integer pageSize);

    int selectTotalCount();

    void deleteAll(Integer[] ids);
}
