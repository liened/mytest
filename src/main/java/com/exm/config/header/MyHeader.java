package com.exm.config.header;

import lombok.Data;

@Data
public class MyHeader {
    public static final String MY_TOKEN = "X-TOKEN";
    public static final String DIVICE = "X-DIVICE";

    private String token;
    private String divice;

}
