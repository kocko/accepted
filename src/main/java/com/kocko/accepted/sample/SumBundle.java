package com.kocko.accepted.sample;

import com.kocko.accepted.problem.ProblemBundle;
import com.kocko.accepted.random.RandomInt;
import com.kocko.accepted.utils.InputReader;

import java.io.PrintWriter;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class SumBundle extends ProblemBundle {

    private SumBundle(String problemName) {
        super(problemName);
    }

    @Override
    protected Consumer<PrintWriter> input() {
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

    @Override
    protected BiConsumer<InputReader, PrintWriter> output() {
        return (in, out) -> {
            int n = in.ni();
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += in.nl();
            }
            out.println(sum);
        };
    }

    public static void main(String[] args) throws Exception {
        new SumBundle("Sum").run(new Sum());
    }

}
