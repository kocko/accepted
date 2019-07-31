package com.kocko.accepted.sample;

import com.kocko.accepted.checker.LineByLineChecker;
import com.kocko.accepted.problem.ProblemBundle;
import com.kocko.accepted.random.RandomInt;
import com.kocko.accepted.utils.InputReader;

import java.io.PrintWriter;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BBundle extends ProblemBundle<LineByLineChecker> {

  public BBundle(String problemName) {
    super(problemName, new LineByLineChecker(problemName));
  }

  public static void main(String[] args) throws Exception {
    new BBundle("BBundle").run(new B());
  }

  @Override
  protected Consumer<PrintWriter> input() {
    return (out) -> {
      RandomInt random = new RandomInt();
      int n = random.next(1, 15);
      out.println(n);
      RandomInt degree = new RandomInt();
      for (int i = 0; i < n; i++) {
        out.println(degree.next(1, 180));
      }
    };
  }

  @Override
  protected BiConsumer<InputReader, PrintWriter> output() {
    return (in, out) -> {
      int n = in.ni();
      int[] x = new int[n];
      for (int i = 0; i < n; i++) {
        x[i] = in.ni();
      }
      boolean ok = false;
      for (int i = 0; i < (1 << n) - 1; i++) {
        int deg = 0;
        for (int j = 0; j < n; j++) {
          int bit = 1 << j;
          if ((i & bit) != 0) {
            deg += x[j];
          } else {
            deg -= x[j];
          }
          if (deg >= 360) deg -= 360;
          if (deg < 0) deg += 360;
        }
        if (deg == 0) {
          ok = true;
          break;
        }
      }
      out.println(ok ? "YES" : "NO");
    };
  }
}
