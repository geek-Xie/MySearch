package com.mysearch.Service;

import com.mysearch.Entity.RequestBody.SearchData;
import com.mysearch.Entity.SearchHistory;

import java.util.List;

public interface SearchService {
    String getURL(SearchData searchData);

    List<SearchHistory> historyList(String user_email);
}
