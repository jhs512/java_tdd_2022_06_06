package com.exam.exam1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private int id;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String title;
    private String body;

    public String getRegDateFormat1() {
        return regDate.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"));
    }

    public Object getUpdateDateFormat1() {
        return updateDate.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"));
    }

    public Article(int id, String title, String body) {
        this(id, LocalDateTime.now(), LocalDateTime.now(), title, body);
    }
}
