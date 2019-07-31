package com.kocko.accepted.sample;

import com.kocko.accepted.checker.LineByLineChecker;
import com.kocko.accepted.problem.ProblemBundle;
import com.kocko.accepted.random.RandomInt;
import com.kocko.accepted.utils.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class RevealCardsBundle extends ProblemBundle<LineByLineChecker> {

  public RevealCardsBundle(String problemName) {
    super(problemName, new LineByLineChecker(problemName));
  }

  public static void main(String[] args) throws Exception {
    new RevealCardsBundle("RevealCards").run(new RevealCards());
  }

  @Override
  protected Consumer<PrintWriter> input() {
    final int MAX_N = 10;
    final int MAX_CARD = 100;
    return (out) -> {
      RandomInt randomInt = new RandomInt();
      int n = randomInt.next(1, MAX_N);
      out.println(n);
      for (int i = 0; i < n; i++) {
        out.print(randomInt.next(1, MAX_CARD));
        out.print(' ');
      }
    };
  }

  @Override
  protected BiConsumer<InputReader, PrintWriter> output() {
    return (in, out) -> {
      int n = in.ni();
      int[] deck = new int[n];
      for (int i = 0; i < n; i++) {
        deck[i] = in.ni();
      }
      Arrays.sort(deck);
      if (n == 1) {
        System.out.println(deck[0]);
        return;
      }
      int[] extended = new int[2 * n];

      extended[2 * n - 1] = deck[n - 1];
      extended[2 * n - 2] = deck[n - 2];

      int current = n - 2, bottom = 2 * n - 1;
      int idx = 2 * n - 3;
      while (--current >= 0) {
        extended[idx] = extended[bottom--];
        extended[idx - 1] = deck[current];
        idx -= 2;
      }

      int[] result = new int[n];
      for (int i = 2; i < n + 2; i++) {
        result[i - 2] = extended[i];
      }
      for (int card : result) {
        out.print(card);
        out.print(' ');
      }
    };
  }
}
