package com.exam.exam1;

import com.exam.exam1.util.String2;
import com.exam.exam1.util.TestUtil;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
    public String appWaitCmd(App app, String input) {
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        app.waitCmd();

        final String outputStr = output.toString();
        TestUtil.clearSetOutToByteArray(output);

        return outputStr;
    }

    public String appWaitCmdThenClose(String input) {
        final Scanner sc = TestUtil.getScanner(input);
        final App app = new App(sc);

        String output = appWaitCmd(app, input);

        sc.close();

        return output;
    }

    @Test
    public void 게시물_등록() {
        String2 output = new String2(appWaitCmdThenClose("""
                등록
                제목
                내용
                종료
                """.stripIndent()));

        assertTrue(output.findAndDeleteBeforeAll("1번 게시물이 생성되었습니다.") != -1);
    }

    @Test
    public void 게시물_2개_등록() {
        String2 output = new String2(appWaitCmdThenClose("""
                등록
                제목
                내용
                
                등록
                제목
                내용

                종료
                """.stripIndent()));
        assertTrue(output.findAndDeleteBeforeAll("1번 게시물이 생성되었습니다.") != -1);
        assertTrue(output.findAndDeleteBeforeAll("2번 게시물이 생성되었습니다.") != -1);
    }

    @Test
    public void 게시물_목록() {
        String2 output = new String2(appWaitCmdThenClose("""
                등록
                제목1
                내용1
                                
                등록
                제목2
                내용2

                목록

                종료
                """.stripIndent()));
        assertTrue(output.findAndDeleteBeforeAll("제목2") != -1);
        assertTrue(output.findAndDeleteBeforeAll("제목1") != -1);
    }

    @Test
    public void _1번_게시물_상세내용() {
        String2 output = new String2(appWaitCmdThenClose("""
                등록
                제목1
                내용1

                등록
                제목2
                내용2

                상세?id=1

                종료
                """.stripIndent()));
        assertTrue(output.findAndDeleteBeforeAll("제목1") != -1);
        assertTrue(output.findAndDeleteBeforeAll("내용1") != -1);
    }

    @Test
    public void _2번_게시물_상세내용() {
        String2 output = new String2(appWaitCmdThenClose("""
                등록
                제목1
                내용1

                등록
                제목2
                내용2

                상세?id=2

                종료
                """.stripIndent()));
        assertTrue(output.findAndDeleteBeforeAll("제목2") != -1);
        assertTrue(output.findAndDeleteBeforeAll("내용2") != -1);
    }

    @Test
    public void _2번_게시물_수정() {
        String2 output = new String2(appWaitCmdThenClose("""
                등록
                제목1
                내용1
                           
                등록
                제목2
                내용2

                수정?id=2
                새 제목2
                새 내용2
                                
                상세?id=2

                종료
                """.stripIndent()));
        assertTrue(output.findAndDeleteBeforeAll("새 제목2") != -1);
        assertTrue(output.findAndDeleteBeforeAll("새 내용2") != -1);
    }
}