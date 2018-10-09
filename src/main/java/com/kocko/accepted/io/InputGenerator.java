package com.kocko.accepted.io;

import java.io.File;
import java.io.PrintWriter;
import java.util.function.Consumer;

public class InputGenerator {
    
    private int test = 0;
    
    public void generateTestSuite(String problem, Consumer<PrintWriter> testCase, int count) throws Exception {
        PrintWriter out;
        while (count-- > 0) {
            String fileName = String.format("%s-%02d.in", problem, ++test);
            out = new PrintWriter(new File(fileName));
            testCase.accept(out);
            out.flush();
            out.close();
        }
    }
    
}
