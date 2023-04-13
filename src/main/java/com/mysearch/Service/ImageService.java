package com.mysearch.Service;

import com.mysearch.Entity.Response.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ImageService {
    String getImageUrl(String user_email);

    ResponseBody uploadImage(MultipartFile file, HttpServletRequest req);

    ResponseBody showImage(String user_email);
}
