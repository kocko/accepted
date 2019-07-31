package com.kocko.accepted.sample;

import com.kocko.accepted.problem.Solvable;
import com.kocko.accepted.utils.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class RevealCards implements Solvable {

  @Override
  public void solve(InputReader in, PrintWriter out) {
    int n = in.ni();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = in.ni();
    }
    int[] result = getResultArray(arr);
    for (int i = 0; i < n; i++) {
      out.print(result[i]);
      out.print(' ');
    }
  }

  private int[] getResultArray(final int[] arr) {
    Arrays.sort(arr);
    if (arr.length <= 2) {
      return arr;
    }
    final int[] res = new int[arr.length];
    int smallIndex = 0;
    int largeIndexFront = 1;
    int largeIndexRear = arr.length % 2 == 0 ? arr.length - 1 : arr.length - 2;
    boolean oscillator = true;
    for (int i = 0, j = arr.length - 1; i <= j; i++, j--) {
      res[smallIndex] = arr[i];
      smallIndex += 2;
      if (i != j) {
        if (!oscillator) {
          res[largeIndexFront] = arr[j];
          largeIndexFront += 2;
        } else {
          res[largeIndexRear] = arr[j];
          largeIndexRear -= 2;
        }
        oscillator = !oscillator;
      }
    }
    return res;
  }

}
