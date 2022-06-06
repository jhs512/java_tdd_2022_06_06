package com.exam.exam1;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class UrlParser {
    @Getter
    private final String url;
    @Getter
    private final Map<String, String> params = new HashMap<>();
    @Getter
    private final String path;

    public UrlParser(String url) {
        this.url = url.trim();
        String[] urlBits = this.url.split("\\?", 2);

        this.path = urlBits[0].trim();

        if ( urlBits.length >= 2 ) {
            String queryStr = urlBits[1].trim();
            String[] queryStrBits = queryStr.split("&");
            for ( String queryStrBit : queryStrBits ) {
                String[] queryParamBits = queryStrBit.split("=", 2);

                if ( queryParamBits.length >= 2 ) {
                    String key = queryParamBits[0].trim();
                    String value = queryParamBits[1].trim();
                    params.put(key, value);
                }
            }
        }
    }

    public int getParam(String key, Class<Integer> cls, int defaultValue) {
        try {
            return Integer.parseInt(params.get(key));
        }
        catch ( Exception e ) {
            return defaultValue;
        }
    }
}
