package com.mysearch.Service.Impl;

import com.mysearch.Entity.RequestBody.SearchData;
import com.mysearch.Entity.Response.ResponseBody;
import com.mysearch.Entity.Response.ResponseBodyFactory;
import com.mysearch.Entity.SearchHistory;
import com.mysearch.Mapper.SearchMapper;
import com.mysearch.Service.SearchService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchMapper searchMapper;

    @Override
    public String getURL(SearchData searchData) {
        String engine = searchData.getEngine();
        String search_content = searchData.getSearch_content();
        String return_url = "";
        switch (engine.toLowerCase(Locale.ROOT)) {
            case "baidu":
                return_url = "https://www.baidu.com/s?pn=0&wd=" + searchData.getSearch_content();
                break;
            case "bing":
                return_url = "https://cn.bing.com/search?q=" + search_content + "&search=&form=CHRDEF";
                break;
            case "google":
                return_url = "https://www.google.com.hk/search?q=" + search_content;
                break;
        }
        String user_email = searchData.getUser_email();
        Date date = new Date();
        searchMapper.addSearchItem(engine, user_email, search_content, date, return_url);
        return return_url;
    }

    @Override
    public List<SearchHistory> historyList(String user_email) {
        return searchMapper.getHistory(user_email);
    }

    @Override
    public ResponseBody searchProcess(SearchData searchData) {
        String return_url = getURL(searchData);
        Map<String, Object> data = new HashMap<>();
        data.put("url", return_url);
        return ResponseBodyFactory.buildResponseBody("200", "访问成功", data);
    }
}
