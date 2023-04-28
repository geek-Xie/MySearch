package com.mysearch.Service.Impl;

import com.mysearch.Entity.Response.ResponseBody;
import com.mysearch.Entity.Response.ResponseBodyFactory;
import com.mysearch.Entity.Response.StatusCode;
import com.mysearch.Mapper.UserMapper;
import com.mysearch.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String getImageUrl(String user_email) {
        return userMapper.getImageUrlByEmail(user_email);
    }

    @Override
    public ResponseBody uploadImage(MultipartFile file, HttpServletRequest req) {
        // 放在本地项目 目录
        String filePath = System.getProperties().getProperty("user.dir");
        if (System.getProperties().getProperty("os.name").toLowerCase(Locale.ROOT).startsWith("windows")) {
            filePath = filePath + "\\src\\main\\resources\\static\\img";
        } else if (System.getProperties().getProperty("os.name").toLowerCase().startsWith("mac")) {
            filePath = filePath + "/src/main/resources/static/img";
            System.out.println(filePath);
        }
        File folder = new File(filePath);

        if (!folder.exists()) {
            folder.mkdirs();
        }
        // 生成新的文件名
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));
        Map<String, Object> data = new HashMap<>();
        String status_code = "";
        String msg = "";
        try {
            // 保存文件到指定路径
            file.transferTo(new File(folder, newName));
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/img/" + newName;

            status_code = StatusCode.SUCCEED;
            data.put("name", oldName);
            msg = "upload successful";
            data.put("url", url);

        } catch (IOException e) {
            status_code = StatusCode.UPLOADERROR;
            msg = e.getMessage();
        }
        return ResponseBodyFactory.buildResponseBody(status_code, msg, data);
    }

    @Override
    public ResponseBody showImage(String user_email) {
        String image_url = getImageUrl(user_email);
        Map<String, Object> data = new HashMap<>();
        data.put("url", image_url);
        return ResponseBodyFactory.buildResponseBody(StatusCode.SUCCEED, "", data);
    }
}
