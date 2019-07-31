package com.kocko.accepted.problem;

import com.kocko.accepted.checker.Checker;
import com.kocko.accepted.io.InputGenerator;
import com.kocko.accepted.io.OutputGenerator;
import com.kocko.accepted.utils.InputReader;

import java.io.PrintWriter;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class ProblemBundle<T extends Checker> {

  private final String problemName;
  private final InputGenerator inputGenerator;
  private final OutputGenerator outputGenerator;
  private final T checker;

  public ProblemBundle(String problemName, T checker) {
    this.problemName = problemName;
    this.inputGenerator = new InputGenerator();
    this.outputGenerator = new OutputGenerator();
    this.checker = checker;
  }

  protected abstract Consumer<PrintWriter> input();

  protected void generateInput() throws Exception {
    inputGenerator.generateTestSuite(problemName, input(), 20);
  }

  protected abstract BiConsumer<InputReader, PrintWriter> output();

  protected void generateOutput() throws Exception {
    outputGenerator.solve(problemName, output(), 20);
  }

  protected void runTester(Solvable solution) throws Exception {
    checker.testSolution(solution, 20);
  }

  public void run(Solvable solution) throws Exception {
    generateInput();
    generateOutput();
    runTester(solution);
  }

}
