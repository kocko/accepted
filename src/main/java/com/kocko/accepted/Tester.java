package com.kocko.accepted;

import com.kocko.accepted.problem.Solvable;
import com.kocko.accepted.problem.Verdict;
import com.kocko.accepted.utils.InputReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import static java.lang.String.format;

public class Tester {

    private final String problemName;
    private final int timeLimit;

    public Tester(String problemName, int timeLimit) {
        this.problemName = problemName;
        this.timeLimit = timeLimit;
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

    private void validate(int testCase) throws Exception {
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
