package com.mysearch.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {
    private int user_id;
    private String user_name;
    private String user_email;
    private String user_password;
    private String image_url;
}
