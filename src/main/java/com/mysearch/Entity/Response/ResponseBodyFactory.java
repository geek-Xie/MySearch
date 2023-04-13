package com.mysearch.Entity.Response;

import java.util.Map;

public class ResponseBodyFactory {
    public static ResponseBody buildResponseBody(String code, String msg, Map<String, Object> data) {
        return new ResponseBody(code, msg, data);
    }
}
