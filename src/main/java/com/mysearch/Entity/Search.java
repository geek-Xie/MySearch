package com.mysearch.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class Search {
    private int search_id;
    private String search_engine;
    private String user_email;
    private String search_content;
    private Date search_date;
    private String return_url;
}
