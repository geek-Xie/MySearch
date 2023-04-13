package com.mysearch.Controller;

import com.mysearch.Entity.Response.ResponseBody;
import com.mysearch.Entity.Response.ResponseBodyFactory;
import com.mysearch.Entity.Response.StatusCode;
import com.mysearch.Service.Impl.ImageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/personal/image")
public class ImageController {
    @Autowired
    private ImageServiceImpl imageService;

    @RequestMapping("/upload")
    public ResponseBody upload(MultipartFile file, HttpServletRequest req) {
        return imageService.uploadImage(file, req);
    }

    @GetMapping("/{user_email}")
    public ResponseBody show(@PathVariable String user_email) {
        return imageService.showImage(user_email);
    }
}
