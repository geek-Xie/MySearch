package com.mysearch.Entity.Response;

import lombok.Data;

@Data
public class StatusCode {
    public final static String SUCCEED = "200";
    public final static String ERROR = "400";
    public final static String UNKNOWNUSERERROR = "401";
    public final static String WRONGPASSWORDERROR = "402";
    public final static String UPLOADERROR = "403";
    public final static String USEREXISTED = "404";
}
