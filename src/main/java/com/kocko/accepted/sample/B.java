package com.kocko.accepted.sample;

import com.kocko.accepted.problem.Solvable;
import com.kocko.accepted.utils.InputReader;

import java.io.PrintWriter;
import java.util.ArrayDeque;

public class B implements Solvable {

  @Override
  public void solve(InputReader in, PrintWriter out) {
    int n = in.ni();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.ni();
    }
    ArrayDeque<Integer> queue = new ArrayDeque<>();
    queue.push(a[0]);
    queue.push(360 - a[0]);

    for (int i = 1; i < n; i++) {
      int si = queue.size();
      while (si-- > 0) {
        int t = queue.pollFirst();
        queue.pollFirst();
        queue.push((t + a[i]) % 360);
        queue.push((t + (360 - a[i])) % 360);
      }
    }
    while (!queue.isEmpty()) {
      int t = queue.pollFirst();
      if (t != 0) {
        out.println("YES");
        return;
      }
    }
    out.println("NO");
  }

}
