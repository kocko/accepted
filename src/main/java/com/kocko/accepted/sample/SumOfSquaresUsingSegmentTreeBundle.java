package com.kocko.accepted.sample;

import com.kocko.accepted.checker.LineByLineChecker;
import com.kocko.accepted.problem.ProblemBundle;
import com.kocko.accepted.random.RandomInt;
import com.kocko.accepted.utils.InputReader;

import java.io.PrintWriter;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class SumOfSquaresUsingSegmentTreeBundle extends ProblemBundle<LineByLineChecker> {

  public SumOfSquaresUsingSegmentTreeBundle(String problemName) {
    super(problemName, new LineByLineChecker(problemName));
  }

  public static void main(String[] args) throws Exception {
    new SumOfSquaresUsingSegmentTreeBundle("SumOfSquaresUsingSegmentTree").run(new SumOfSquaresUsingSegmentTree());
  }

  @Override
  protected Consumer<PrintWriter> input() {
    return out -> {
      out.println(1);
      RandomInt integerGenerator = new RandomInt();
      final int MIN_N = 1, MAX_N = 100;
      final int MIN_Q = 1, MAX_Q = 15;
      final int MIN_A = 1, MAX_A = 10;
      int n = integerGenerator.next(MIN_N, MAX_N), q = integerGenerator.next(MIN_Q, MAX_Q);
      out.print(n);
      out.print(' ');
      out.println(q);
      for (int i = 0; i < n; i++) {
        out.print(integerGenerator.next(MIN_A, MAX_A));
        out.print(' ');
      }
      out.println();
      for (int i = 0; i < q; i++) {
        int type = integerGenerator.next(0, 2);
        if (type == 0) {
          int a = integerGenerator.next(1, n);
          int b = integerGenerator.next(a, n);
          int value = integerGenerator.next(-1000, 1000);
          out.printf("%d %d %d %d", type, a, b, value);
        } else if (type == 1) {
          int a = integerGenerator.next(1, n);
          int b = integerGenerator.next(a, n);
          int delta = integerGenerator.next(-1000, 1000);
          out.printf("%d %d %d %d", type, a, b, delta);
        } else {
          int a = integerGenerator.next(1, n);
          int b = integerGenerator.next(a, n);
          out.printf("%d %d %d", type, a, b);
        }
        out.println();
      }
    };
  }

  @Override
  protected BiConsumer<InputReader, PrintWriter> output() {
    return (in, out) -> {
      int t = in.ni();
      while (t-- > 0) {
        int n = in.ni(), q = in.ni();
        long[] x = new long[n];
        for (int i = 0; i < n; i++) {
          x[i] = in.nl();
        }
        out.println("Case 1:");
        while (q-- > 0) {
          int type = in.ni(), a = in.ni() - 1, b = in.ni() - 1;
          if (type == 0) {
            long value = in.nl();
            for (int i = a; i <= b; i++) {
              x[i] = value;
            }
          } else if (type == 1) {
            long delta = in.nl();
            for (int i = a; i <= b; i++) {
              x[i] += delta;
            }
          } else {
            long squares = 0;
            for (int i = a; i <= b; i++) {
              squares += (x[i] * x[i]);
            }
            out.println(squares);
          }
        }
      }
    };
  }
}
