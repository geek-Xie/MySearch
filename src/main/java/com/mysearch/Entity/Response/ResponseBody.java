package com.mysearch.Entity.Response;

import lombok.Data;

import java.util.Map;

@Data
public class ResponseBody {
    private String code;
    private String msg;
    private Map<String, Object> data;

    public ResponseBody(String code, String msg, Map<String, Object> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
