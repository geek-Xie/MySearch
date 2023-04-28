package com.mysearch.Service;

import com.mysearch.Entity.Movie;
import com.mysearch.Entity.RequestBody.DouSearchItem;
import com.mysearch.Entity.Response.ResponseBody;

import java.util.List;

public interface DouService {
    ResponseBody searchResult(String search_content) throws Exception;

    ResponseBody getAllMovie();

    ResponseBody addMovie(DouSearchItem douSearchItem);
}
