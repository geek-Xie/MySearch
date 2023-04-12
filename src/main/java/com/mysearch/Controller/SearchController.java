package com.mysearch.Controller;

import com.mysearch.Entity.Response.ResponseBody;
import com.mysearch.Entity.RequestBody.SearchData;
import com.mysearch.Service.Impl.SearchServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchServiceImpl searchService;

    @RequestMapping("/searching")
    public ResponseBody test(@RequestBody SearchData searchData) {
        String return_url = searchService.getURL(searchData);
        Map<String, Object> data = new HashMap<>();
        data.put("url", return_url);
        return new ResponseBody("200", "访问成功", data);
    }
}
