package com.kocko.accepted.io;

import java.io.File;
import java.io.PrintWriter;
import java.util.function.Consumer;

public class InputGenerator {

  private int test = 0;

  public void generateTestSuite(String problem, Consumer<PrintWriter> testCase, int count) throws Exception {
    PrintWriter out;
    String folder = problem.toLowerCase();
    if (createFolder(folder)) {
      while (count-- > 0) {
        String fileName = String.format("%02d.in", ++test);
        out = new PrintWriter(new File(folder + "\\" + fileName));
        testCase.accept(out);
        out.flush();
        out.close();
      }
    }
  }

  private boolean createFolder(String folder) {
    File directory = new File(folder);
    if (!directory.exists()) {
      return directory.mkdir();
    }
    return false;
  }

}
