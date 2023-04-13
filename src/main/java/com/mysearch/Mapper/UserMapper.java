package com.mysearch.Mapper;

import com.mysearch.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user;")
    List<User> findAllUser();

    @Insert("insert into user set user_name = #{user_name}, user_email = #{user_email}, user_password = #{user_password}, image_url = #{image_url}")
    void registerUser(String user_name, String user_email, String user_password, String image_url);

    @Select("select * from user where user_email = #{user_email}")
    User getUserByEmail(String user_email);

    @Select("select image_url from user where user_email = #{user_email}")
    String getImageUrlByEmail(String user_email);


}
