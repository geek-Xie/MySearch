package com.mysearch.Service.Impl;

import com.mysearch.Entity.Movie;
import com.mysearch.Entity.RequestBody.DouSearchItem;
import com.mysearch.Entity.Response.ResponseBody;
import com.mysearch.Entity.Response.ResponseBodyFactory;
import com.mysearch.Entity.Response.StatusCode;
import com.mysearch.Mapper.DouMapper;
import com.mysearch.Service.DouService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DouServiceImpl implements DouService {
    @Autowired
    private DouMapper douMapper;

    @Override
    public ResponseBody searchResult(String search_content) throws Exception {
        List<Movie> movieList = new ArrayList<>();
        Elements elements = getElements(search_content);
        for (Element el : elements) {
            if (isMovie(el)) {
                String pic_url = getPictureUrl(el);
                String movie_name = getMovieName(el);
                String cast = getCast(el);
                String rating_num = getRatingNum(el);
//                String movie_description = getMovieDescription(el);
                movieList.add(new Movie(pic_url, movie_name, cast, rating_num));
            }
        }
        Map<String, Object> data = new HashMap<>();
        if (movieList.size() > 6) {
            movieList = movieList.subList(0, 6);
        }
        data.put("movieList", movieList);
        return ResponseBodyFactory.buildResponseBody("200", "", data);
    }

    @Override
    public ResponseBody getAllMovie() {
        List<Movie> movies = douMapper.findAll();
        if (movies.size() > 0) {
            Map<String, Object> data = new HashMap<>();
            data.put("movies", movies);
            return ResponseBodyFactory.buildResponseBody("200", "获取列表成功", data);
        } else {
            return ResponseBodyFactory.buildResponseBody("200", "列表为空", null);
        }
    }

    @Override
    public ResponseBody addMovie(DouSearchItem douSearchItem) {
        String picture_url = douSearchItem.getPicture_url();
        String cast = douSearchItem.getCast();
        String movie_name = douSearchItem.getMovie_name();
        String rating_num = douSearchItem.getRating_num();
        if (picture_url == null) {
            return ResponseBodyFactory.buildResponseBody(StatusCode.PICTUREURLERROR, "图片链接为空", null);
        }
        if (isMovieExist(picture_url)) {
            return ResponseBodyFactory.buildResponseBody(StatusCode.MOVIEEXIST, "当前已存在", null);
        }
        douMapper.addMovie(picture_url, movie_name, cast, rating_num);
        return ResponseBodyFactory.buildResponseBody(StatusCode.SUCCEED, "成功添加", null);
    }

    private boolean isMovieExist(String picture_url) {
        Movie movie = douMapper.findByUrl(picture_url);
        return movie != null;
    }

    private boolean isMovie(Element el) {
        String span_str = el.select("span").toString();
        Pattern pattern = Pattern.compile("(?<=\\[)[^]]+");
        Matcher matcher = pattern.matcher(span_str);
        if (matcher.find()) {
            return matcher.group().equals("电影") || matcher.group().equals("电视剧");
        }
        return false;
    }

    private String getMovieName(Element el) {
        String a_str = el.select("a").get(1).toString();
        Pattern pattern = Pattern.compile("(?<=\\>)[^<]+");
        Matcher matcher = pattern.matcher(a_str);
        String movie_name = "";
        if (matcher.find()) {
            movie_name = matcher.group().trim();
        }
        return movie_name;
    }

    private String getMovieDescription(Element el) {
        String p_str = el.select("p").toString();
        return p_str;
    }

    private String getCast(Element el) {
        String cast_str = el.select("div.rating-info").select("span.subject-cast").toString();
        Pattern pattern = Pattern.compile("(?<=\\>)[^<]+");
        Matcher matcher = pattern.matcher(cast_str);
        String cast = "";
        while (matcher.find()) {
            cast = matcher.group();
        }
        return cast;
    }

    private Elements getElements(String search_content) throws Exception {
        String url = "https://www.douban.com/search?q=" + search_content;
        Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
        Element elements = document.getElementById("wrapper").getElementById("content");
        return elements.select("div.search-result").select("div.result-list").select("div.result");

    }

    private String getPictureUrl(Element el) {
        String pic_str = el.select("a.nbg").select("img").toString();
        Pattern pattern = Pattern.compile("(?<=\\\").*?(?=\\\")");
        Matcher m = pattern.matcher(pic_str);
        String pic_url = "";
        while (m.find()) {
            pic_url = m.group();
        }
        return pic_url;
    }

    private String getRatingNum(Element el) {
        String rating_str = el.select("div.rating-info").select("span.rating_nums").toString();
        Pattern pattern = Pattern.compile("(?<=\\>)[^<]+");
        Matcher matcher = pattern.matcher(rating_str);
        String rating_num = "";
        while (matcher.find()) {
            rating_num = matcher.group();
        }
        return rating_num;
    }
}
