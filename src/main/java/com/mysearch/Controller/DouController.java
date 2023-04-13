package com.mysearch.Controller;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

//@RestController
//@RequestMapping("/dou")
//@CrossOrigin(origins = "*")
//@Slf4j
public class DouController {
    public static void main(String[] args) throws Exception {
        String search_name = "复仇者联盟";
        String url = "https://search.douban.com/movie/subject_search?search_text=" + search_name;
        Document document = Jsoup.connect(url).get();
        Elements bd = document.select("#root > div > div._yc8sb4vem > div._yrj7kimep > div:nth-child(1)");
        for (Element el : bd) {
            System.out.println(el.select("#root > div > div._yc8sb4vem > div._yrj7kimep > div:nth-child(1) > div:nth-child(1) > div > div > div.meta.abstract").text());
        }
//        System.out.println(bd);
//        Document document= Jsoup.parse(new URL("https://www.baidu.com"),1000);
//        System.out.println(document);
    }

}
