package com.kocko.accepted.io;

import com.kocko.accepted.utils.InputReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.function.BiConsumer;

public class OutputGenerator {

  public void solve(String problem, BiConsumer<InputReader, PrintWriter> brute, int tests) throws Exception {
    InputReader in;
    PrintWriter out;

    for (int test = 1; test <= tests; test++) {
      String input = String.format("%s-%02d.in", problem, test);
      String output = String.format("%s-%02d.out", problem, test);
      in = new InputReader(new FileInputStream(new File(input)));
      out = new PrintWriter(new File(output));
      brute.accept(in, out);
      out.close();
    }
  }

}
