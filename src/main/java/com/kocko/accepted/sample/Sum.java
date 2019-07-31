package com.kocko.accepted.sample;

import com.kocko.accepted.problem.Solvable;
import com.kocko.accepted.utils.InputReader;

import java.io.PrintWriter;

public class Sum implements Solvable {

  @Override
  public void solve(InputReader in, PrintWriter out) {
    int n = in.ni();
    long sum = 0;
    for (int i = 0; i < n; i++) {
      sum += in.nl();
    }
    out.println(sum);
  }

}
