package com.mysearch.Entity;

import lombok.Data;

import java.util.List;
@Data
public class Movie {
    private String picture_url;
    private String movie_name;
    private String cast;
    private String rating_num;
//    private String description;

    public Movie(String picture_url, String movie_name, String cast, String rating_num) {
        this.picture_url = picture_url;
        this.movie_name = movie_name;
        this.cast = cast;
        this.rating_num = rating_num;
    }
}
