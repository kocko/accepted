package com.kocko.accepted.problem;

import com.kocko.accepted.Tester;
import com.kocko.accepted.io.InputGenerator;
import com.kocko.accepted.io.OutputGenerator;
import com.kocko.accepted.utils.InputReader;

import java.io.PrintWriter;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class ProblemBundle {

    private final String problemName;
    private final InputGenerator inputGenerator;
    private final OutputGenerator outputGenerator;
    private final Tester tester;

    public ProblemBundle(String problemName) {
        this.problemName = problemName;
        this.inputGenerator = new InputGenerator();
        this.outputGenerator = new OutputGenerator();
        this.tester = new Tester(problemName, 2000);
    }

    protected abstract Consumer<PrintWriter> input();

    protected void generateInput() throws Exception {
        inputGenerator.generateTestSuite(problemName, input(), 25);
    }

    protected abstract BiConsumer<InputReader, PrintWriter> output();

    protected void generateOutput() throws Exception {
        outputGenerator.solve(problemName, output(), 25);
    }

    protected void runTester(Solvable solution) throws Exception {
        tester.testSolution(solution, 25);
    }

    public void run(Solvable solution) throws Exception {
        generateInput();
        generateOutput();
        runTester(solution);
    }

}
