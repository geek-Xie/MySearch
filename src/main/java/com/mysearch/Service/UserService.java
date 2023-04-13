package com.mysearch.Service;

import com.mysearch.Entity.RequestBody.LoginData;
import com.mysearch.Entity.Response.ResponseBody;
import com.mysearch.Entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();

    ResponseBody registerUser(User user);

    User getUserByEmail(String user_email);

    ResponseBody loginProcess(LoginData loginData);

    ResponseBody getSearchHistory(String user_email);
}
