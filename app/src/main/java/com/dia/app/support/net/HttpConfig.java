package com.dia.app.support.net;

import okhttp3.MediaType;

public class HttpConfig {

    public static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse(CONTENT_TYPE_JSON);

    public static final int CONNECT_TIME_OUT = 60;
    public static final int READ_TIME_OUT = 60;
    public static final int WRITE_TIME_OUT = 60;
}
