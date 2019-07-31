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
    String folder = problem.toLowerCase();
    for (int test = 1; test <= tests; test++) {
      String input = String.format("%02d.in", test);
      String output = String.format("%02d.out", test);
      in = new InputReader(new FileInputStream(new File(folder + "\\" + input)));
      out = new PrintWriter(new File(folder + "\\" + output));
      brute.accept(in, out);
      out.close();
    }
  }

}
