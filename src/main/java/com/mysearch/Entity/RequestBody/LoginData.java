package com.mysearch.Entity.RequestBody;

import lombok.Data;

@Data
public class LoginData {
    private String user_email;
    private String user_password;
}
