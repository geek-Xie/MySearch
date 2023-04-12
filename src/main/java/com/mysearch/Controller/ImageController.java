package com.mysearch.Controller;

import com.mysearch.Entity.Response.ResponseBody;
import com.mysearch.Entity.Response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(value = "*")
@Slf4j
@RequestMapping("/personal/image")
public class ImageController {

    @RequestMapping("/upload")
    public ResponseBody uploadImage(MultipartFile file, HttpServletRequest req) {

        // 放在本地项目 目录
        String filePath = "D:\\mycode\\MySearch\\src\\main\\resources\\static\\img";
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
        return new ResponseBody(status_code, msg, data);
    }
}
