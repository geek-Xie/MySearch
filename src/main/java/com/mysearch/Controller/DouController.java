package com.mysearch.Controller;

import com.mysearch.Entity.Movie;
import com.mysearch.Entity.RequestBody.DouSearchItem;
import com.mysearch.Entity.Response.ResponseBody;
import com.mysearch.Entity.Response.ResponseBodyFactory;
import com.mysearch.Service.Impl.DouServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/dou")
@CrossOrigin(origins = "*")
@Slf4j
public class DouController {
    @Autowired
    private DouServiceImpl douService;

    @GetMapping("/add/{search_content}")
    public ResponseBody searchResult(@PathVariable String search_content) throws Exception {
        return douService.searchResult(search_content);
    }
    @GetMapping("/all")
    public ResponseBody all() {
        return douService.getAllMovie();
    }

    @PostMapping("/add")
    public ResponseBody addItem(@RequestBody DouSearchItem douSearchItem) {
        return douService.addMovie(douSearchItem);
    }
}