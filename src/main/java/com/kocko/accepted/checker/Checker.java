package com.kocko.accepted.checker;

import com.kocko.accepted.problem.Solvable;
import com.kocko.accepted.utils.InputReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import static java.lang.String.format;

public abstract class Checker {

  private final String problemName;

  public Checker(String problemName) {
    this.problemName = problemName;
  }

  public void testSolution(Solvable solution, int count) throws Exception {
    for (int test = 1; test <= count; test++) {
      InputReader in = createInputReader(test);
      PrintWriter out = createOutputWriter(test);
      solution.solve(in, out);
      in.close();
      out.flush();
      out.close();
      validate(test);
    }
  }

  private InputReader createInputReader(int testCase) throws Exception {
    String inputFile = format("%02d.in", testCase);
    return new InputReader(new FileInputStream(new File(getFolder() + "\\" + inputFile)));
  }

  private PrintWriter createOutputWriter(int testCase) throws Exception {
    String outputFile = format("%02d.mine", testCase);
    return new PrintWriter(new File(getFolder() + "\\" + outputFile));
  }

  protected abstract void validate(int testNumber) throws Exception;

  final String getFolder() {
    return problemName.toLowerCase();
  }
}
