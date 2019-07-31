package com.kocko.accepted.checker;

import com.kocko.accepted.problem.Solvable;
import com.kocko.accepted.utils.InputReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import static java.lang.String.format;

public abstract class Checker {

  final String problemName;

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
    String inputFile = format("%s-%02d.in", problemName, testCase);
    return new InputReader(new FileInputStream(new File(inputFile)));
  }

  private PrintWriter createOutputWriter(int testCase) throws Exception {
    String output = format("%s-%02d.mine", problemName, testCase);
    return new PrintWriter(new File(output));
  }

  protected abstract void validate(int testNumber) throws Exception;
}
