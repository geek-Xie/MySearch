package com.mysearch.Controller;

import com.mysearch.Entity.RequestBody.LoginData;
import com.mysearch.Entity.Response.ResponseBody;
import com.mysearch.Entity.Response.StatusCode;
import com.mysearch.Entity.SearchHistory;
import com.mysearch.Entity.User;
import com.mysearch.Service.Impl.UserServiceImpl;
import com.mysearch.Service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personal")
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private SearchService searchService;

    @GetMapping("/all")
    public List<User> find() {
        return userService.findAllUser();
    }

    @PostMapping("/register")
    public ResponseBody register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseBody login(@RequestBody LoginData loginData) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginData.getUser_email(), loginData.getUser_password());
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return new ResponseBody(StatusCode.UNKNOWNUSERERROR, "用户不存在", null);
        } catch (CredentialsException e) {
            return new ResponseBody(StatusCode.WRONGPASSWORDERROR, "密码错误", null);
        }
        User user = userService.getUserByEmail(loginData.getUser_email());
        Map<String, Object> data = new HashMap<>();
        data.put("user_name", user.getUser_name());
        data.put("user_email", user.getUser_email());
        return new ResponseBody(StatusCode.SUCCEED, "登陆成功", data);
    }

    @GetMapping("/history/{user_email}")
    public ResponseBody searchHistory(@PathVariable String user_email) {
        List<SearchHistory> searchHistories = searchService.historyList(user_email);
        Map<String, Object> data = new HashMap<>();
        data.put("data", searchHistories);
        return new ResponseBody(StatusCode.SUCCEED, "获取搜索历史", data);
    }

    @PostMapping("/image")
    public void image() {
        System.out.println(123123);
    }
}
