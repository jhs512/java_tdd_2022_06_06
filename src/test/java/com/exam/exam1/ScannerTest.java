package com.exam.exam1;

import com.exam.exam1.util.TestUtil;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScannerTest {
    @Test
    public void 단순작동() {
        String input = "안녕";

        Scanner sc = TestUtil.getScanner(input);
        String line = sc.nextLine();

        assertEquals(input, line);

        sc.close();
    }
}
