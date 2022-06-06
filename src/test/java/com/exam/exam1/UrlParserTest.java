package com.exam.exam1;

import com.exam.exam1.UrlParser;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlParserTest {
    @Test
    public void 파라미터_한개() {
        UrlParser urlParser = new UrlParser("조회?id=1");
        Map<String, String> params = urlParser.getParams();

        assertEquals(params.get("id"), "1");
    }

    @Test
    public void 파라미터_여러개() {
        UrlParser urlParser = new UrlParser("조회?id=1&name=홍길동&age=23");
        Map<String, String> params = urlParser.getParams();

        assertEquals(params.get("id"), "1");
        assertEquals(params.get("name"), "홍길동");
        assertEquals(params.get("age"), "23");
    }
}
