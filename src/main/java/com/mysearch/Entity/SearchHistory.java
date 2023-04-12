package com.mysearch.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SearchHistory {
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private Date search_date;
    private String search_content;
    private String return_url;
}
