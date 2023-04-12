package com.mysearch.Service.Impl;

import com.mysearch.Entity.Response.ResponseBody;
import com.mysearch.Entity.Response.StatusCode;
import com.mysearch.Entity.User;
import com.mysearch.Mapper.UserMapper;
import com.mysearch.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public ResponseBody registerUser(User user) {
        String user_email = user.getUser_email();
        User u = userMapper.getUserByEmail(user_email);
        if (u != null) {
            return new ResponseBody(StatusCode.USEREXISTED, "用户已存在，直接进行登录", null);
        }
        String user_name = user.getUser_name();
        String user_password = user.getUser_password();
        String image_url = user.getImage_url();
        userMapper.registerUser(user_name, user_email, user_password, image_url);
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_email", user_email);
        return new ResponseBody(StatusCode.SUCCEED, "注册成功", data);
    }

    @Override
    public User getUserByEmail(String user_email) {
        return userMapper.getUserByEmail(user_email);
    }
}
