package com.mysearch.Mapper;

import com.mysearch.Entity.Movie;
import com.mysearch.Entity.RequestBody.DouSearchItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DouMapper {
    @Select("select * from movie")
    List<Movie> findAll();

    @Insert("insert into movie set picture_url=#{picture_url}, movie_name = #{movie_name}, cast = #{cast}, rating_num = #{rating_num}")
    void addMovie(String picture_url, String movie_name, String cast, String rating_num);

    @Select("select * from movie where picture_url=#{picture_url}")
    Movie findByUrl(String picture_url);
}
