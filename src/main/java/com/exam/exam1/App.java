package com.exam.exam1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner sc;
    private int lastArticleId = 0;
    private List<Article> articleList = new ArrayList<>();

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void waitCmd() {
        while (true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim();

            if ( cmd.length() == 0 ) {
                continue;
            }

            UrlParser urlParser = new UrlParser(cmd);

            if (urlParser.getPath().equals("등록")) {
                System.out.printf("== 등록 ==\n");
                int id = lastArticleId + 1;

                System.out.printf("제목 : ");
                String title = sc.nextLine();

                System.out.printf("내용 : ");
                String body = sc.nextLine();

                Article article = new Article(id, title, body);
                articleList.add(article);

                System.out.printf("%d번 게시물이 생성되었습니다.\n", id);

                lastArticleId = id;
            } else if (urlParser.getPath().equals("목록")) {
                System.out.printf("== 목록 ==\n");

                System.out.printf("번호 / 작성 / 제목\n");

                for (int i = articleList.size() - 1; i >= 0; i--) {
                    Article article = articleList.get(i);

                    System.out.printf("%d / %s / %s\n", article.getId(), article.getRegDateFormat1(), article.getTitle());
                }
            } else if (urlParser.getPath().startsWith("수정")) {
                System.out.printf("== 수정 ==\n");

                int id = urlParser.getParam("id", Integer.class, 0);

                Article article = getArticleById(id);

                if ( article == null ) {
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                    continue;
                }

                System.out.printf("번호 : %d\n", article.getId());
                System.out.printf("작성 : %s\n", article.getRegDateFormat1());
                System.out.printf("제목 : ");
                String title = sc.nextLine();

                System.out.printf("내용 : ");
                String body = sc.nextLine();

                article.setTitle(title);
                article.setBody(body);
                article.setUpdateDate(LocalDateTime.now());

                System.out.printf("%d번 게시물이 수정되었습니다.\n", id);

            } else if (urlParser.getPath().startsWith("상세")) {
                System.out.printf("== 상세내용 ==\n");

                int id = urlParser.getParam("id", Integer.class, 0);

                Article article = getArticleById(id);

                if ( article == null ) {
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                    continue;
                }

                System.out.printf("번호 : %d\n", article.getId());
                System.out.printf("작성 : %s\n", article.getRegDateFormat1());
                System.out.printf("수정 : %s\n", article.getUpdateDateFormat1());
                System.out.printf("제목 : %s\n", article.getTitle());
                System.out.printf("내용 : %s\n", article.getBody());

            } else if (urlParser.getPath().equals("종료")) {
                break;
            }
        }
    }

    private Article getArticleById(int id) {
        for (Article article : articleList) {
            if (article.getId() == id) {
                return article;
            }
        }

        return null;
    }

    public void start() {
        waitCmd();
    }
}
