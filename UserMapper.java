package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
//@Select("select * from user")
    List<User> selectAll();

    void deleteById(Integer id);

    void add(User user);

    User selectById(Integer id);

    void update(User user);

    User login(@Param("name") String name, @Param("password") String password);

    List<User> selectByPage(@Param("offset") int offset,@Param("pageSize") Integer pageSize);

    int selectTotalCount();

    void deleteAll(Integer[] ids);
}
