package com.kocko.accepted.checker;

import com.kocko.accepted.problem.Verdict;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static java.lang.String.format;

public class LineByLineChecker extends Checker {

  public LineByLineChecker(String problemName) {
    super(problemName);
  }

  public void validate(int testCase) throws Exception {
    System.out.print("Running on test " + testCase + " => ");
    System.out.flush();
    Verdict verdict = Verdict.OK;

    final String pattern = "%s-%02d.%s";

    String judgeFileName = format(pattern, problemName, testCase, "out");
    String mineFileName = format(pattern, problemName, testCase, "mine");
    BufferedReader judgeSolution = new BufferedReader(new FileReader(new File(judgeFileName)));
    BufferedReader mineSolution = new BufferedReader(new FileReader(new File(mineFileName)));

    boolean equal = true;
    String judgeLine = judgeSolution.readLine().trim(), myLine = mineSolution.readLine().trim();
    while (judgeLine != null || myLine != null) {
      if (judgeLine == null || myLine == null) {
        equal = false;
        break;
      } else if (!judgeLine.equals(myLine)) {
        equal = false;
        break;
      }
      judgeLine = judgeSolution.readLine();
      myLine = mineSolution.readLine();
    }
    if (!equal) {
      verdict = Verdict.WA;
    }
    printVerdict(testCase, verdict);
    judgeSolution.close();
    mineSolution.close();
  }

  private void printVerdict(int testCase, Verdict verdict) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    if (verdict == Verdict.OK) {
      System.out.println(ANSI_GREEN + "Accepted" + ANSI_RESET);
    } else {
      System.out.println(ANSI_RED + "Wrong answer on test " + testCase + ANSI_RESET);
    }
  }

}
