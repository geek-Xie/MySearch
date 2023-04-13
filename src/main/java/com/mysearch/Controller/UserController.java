package com.mysearch.Controller;

import com.mysearch.Entity.RequestBody.LoginData;
import com.mysearch.Entity.Response.ResponseBody;
import com.mysearch.Entity.User;
import com.mysearch.Service.Impl.UserServiceImpl;
import com.mysearch.Service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return userService.loginProcess(loginData);
    }

    @GetMapping("/history/{user_email}")
    public ResponseBody searchHistory(@PathVariable String user_email) {
        return userService.getSearchHistory(user_email);
    }

    @PostMapping("/image")
    public void image() {
        System.out.println(123123);
    }
}
