package com.mysearch.Entity.RequestBody;

import lombok.Data;

@Data
public class SearchData {
    private String user_email;
    private String search_content;
    private String engine;
}
