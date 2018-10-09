package com.kocko.accepted;

import com.kocko.accepted.problem.Solvable;
import com.kocko.accepted.utils.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class Parking implements Solvable {

    @Override
    public void solve(InputReader in, PrintWriter out) {
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
    }
    
}
