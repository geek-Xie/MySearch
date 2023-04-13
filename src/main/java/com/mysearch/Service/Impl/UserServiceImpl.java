package com.mysearch.Service.Impl;

import com.mysearch.Entity.RequestBody.LoginData;
import com.mysearch.Entity.Response.ResponseBody;
import com.mysearch.Entity.Response.ResponseBodyFactory;
import com.mysearch.Entity.Response.StatusCode;
import com.mysearch.Entity.SearchHistory;
import com.mysearch.Entity.User;
import com.mysearch.Mapper.UserMapper;
import com.mysearch.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SearchServiceImpl searchService;

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
        return ResponseBodyFactory.buildResponseBody(StatusCode.SUCCEED, "注册成功", data);
    }

    @Override
    public User getUserByEmail(String user_email) {
        return userMapper.getUserByEmail(user_email);
    }

    @Override
    public ResponseBody loginProcess(LoginData loginData) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginData.getUser_email(), loginData.getUser_password());
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return ResponseBodyFactory.buildResponseBody(StatusCode.UNKNOWNUSERERROR, "用户不存在", null);
        } catch (CredentialsException e) {
            return ResponseBodyFactory.buildResponseBody(StatusCode.WRONGPASSWORDERROR, "密码错误", null);
        }
        User user = getUserByEmail(loginData.getUser_email());
        Map<String, Object> data = new HashMap<>();
        data.put("user_name", user.getUser_name());
        data.put("user_email", user.getUser_email());
        return ResponseBodyFactory.buildResponseBody(StatusCode.SUCCEED, "登陆成功", data);
    }

    @Override
    public ResponseBody getSearchHistory(String user_email) {
        List<SearchHistory> searchHistories = searchService.historyList(user_email);
        Map<String, Object> data = new HashMap<>();
        data.put("data", searchHistories);
        return ResponseBodyFactory.buildResponseBody(StatusCode.SUCCEED, "获取搜索历史", data);
    }
}
