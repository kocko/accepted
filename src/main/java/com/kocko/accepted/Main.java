package com.kocko.accepted;

import com.kocko.accepted.io.InputGenerator;
import com.kocko.accepted.io.OutputGenerator;
import com.kocko.accepted.random.RandomInt;
import com.kocko.accepted.utils.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Main {

    private final String problemName = "Parking";
    private InputGenerator inputGenerator = new InputGenerator();
    private OutputGenerator outputGenerator = new OutputGenerator();
    private Tester tester = new Tester(problemName, 2000);
    
    private void run() throws Exception {
        inputGenerator.generateTestSuite(problemName, parking_in(), 10);
        outputGenerator.solve(problemName, parkingBruteForce(), 10);
        tester.testSolution(new Parking(), 10);
    }

    private static Consumer<PrintWriter> enormous() {
        return out -> {
            RandomInt integerGenerator = new RandomInt();

            final int MIN_N = 1, MAX_N = 50;
            final int MIN_A = 1, MAX_A = (int) 1e9;

            int n = integerGenerator.next(MIN_N, MAX_N);
            out.println(n);
            for (int i = 0; i < n; i++) {
                int a = integerGenerator.next(MIN_A, MAX_A);
                out.print(a);
                out.print(' ');
            }
        };
    }

    private static Consumer<PrintWriter> parking_in() {
        return out -> {
            RandomInt integerGenerator = new RandomInt();

            final int MIN_T = 1, MAX_T = 100;
            final int MIN_N = 1, MAX_N = 20;
            final int MIN_A = 0, MAX_A = 99;

            int t = integerGenerator.next(MIN_T, MAX_T);
            out.println(t);
            while (t-- > 0) {
                int n = integerGenerator.next(MIN_N, MAX_N);
                out.println(n);
                for (int i = 0; i < n; i++) {
                    int a = integerGenerator.next(MIN_A, MAX_A);
                    out.print(a);
                    out.print(' ');
                }
                out.println();
            }
        };
    }

    private static BiConsumer<InputReader, PrintWriter> bruteForce() {
        return (in, out) -> {
            int n = in.ni();
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += in.nl();
            }
            out.println(sum);
        };
    }

    private static BiConsumer<InputReader, PrintWriter> parkingBruteForce() {
        return (in, out) -> {
            int t = in.ni();
            while (t-- > 0) {
                int n = in.ni();
                int[] x = new int[n];
                for (int i = 0; i < n; i++) {
                    x[i] = in.ni();
                }
                Arrays.sort(x);
                out.println(2 * (x[n - 1] - x[0]));
            }
        };
    }
    
    public static void main(String[] args) throws Exception {
        new Main().run();
    }

}
