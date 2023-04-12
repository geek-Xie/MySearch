package com.mysearch.Mapper;

import com.mysearch.Entity.SearchHistory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface SearchMapper {
    @Insert("insert into search set search_engine = #{search_engine}, user_email = #{user_email}, search_content = #{search_content}," +
            "search_Date = #{search_Date}, return_url = #{return_url}")
    void addSearchItem(String search_engine, String user_email, String search_content, Date search_Date, String return_url);

    @Select("select * from search where user_email = #{user_email}")
    List<SearchHistory> getHistory(String user_email);
}
