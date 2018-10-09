package com.kocko.accepted;

import com.kocko.accepted.problem.ProblemBundle;
import com.kocko.accepted.random.RandomInt;
import com.kocko.accepted.utils.InputReader;

import java.io.PrintWriter;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class FootballAndLieBundle extends ProblemBundle {

    public FootballAndLieBundle() {
        super("FootballAndLie");
    }

    @Override
    protected Consumer<PrintWriter> input() {
        return out -> {
            RandomInt randomInt = new RandomInt();
            int n = randomInt.next(5, 5);
            out.println(n);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        out.print(-1);
                    } else {
                        out.print(randomInt.next(1, 3) - 2);
                    }
                    out.print(' ');
                }
                out.println();
            }
        };
    }

    @Override
    protected BiConsumer<InputReader, PrintWriter> output() {
        return (in, out) -> {
            int n = in.ni();
            int[][] x = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    x[i][j] = in.ni();
                }
            }
            int MAX = (1 << n);
            int[][] solution = null;

            out:
            for (int mask = 0; mask < MAX; mask++) {
                int[][] current = new int[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        boolean liar_i = (mask & (1 << i)) == 0;
                        boolean liar_j = (mask & (1 << j)) == 0;
                        if (liar_i && liar_j) {
                            if (x[i][j] == 1) {
                                if (x[j][i] == 1) continue out;
                                else if (x[j][i] == 0) current[j][i] = 3;
                                else current[j][i] = 3;
                            } else if (x[i][j] == 0) {
                                if (x[j][i] == 1) current[i][j] = 3;
                                else if (x[j][i] == 0) current[i][j] = current[j][i] = 1;
                                else current[i][j] = 3;
                            } else {
                                if (x[j][i] == 1) current[i][j] = 3;
                                else if (x[j][i] == 0) current[j][i] = 3;
                                else current[j][i] = 3;
                            }
                        } else if (liar_i) {
                            if (x[i][j] == 1)
                                if (x[j][i] == 1) current[j][i] = 3;
                                else if (x[j][i] == 0) continue out;
                                else current[j][i] = 3;
                            else if (x[i][j] == 0)
                                if (x[j][i] == 1) current[i][j] = current[j][i] = 1;
                                else current[i][j] = 3;
                            else if (x[i][j] == -1)
                                if (x[j][i] == 0) current[i][j] = 3;
                                else if (x[j][i] == 1) current[j][i] = 3;
                                else if (x[j][i] == -1) current[i][j] = current[j][i] = 1;
                        } else if (liar_j) {
                            if (x[i][j] == 1)
                                if (x[j][i] == 1) current[i][j] = 3;
                                else if (x[j][i] == 0) current[i][j] = current[j][i] = 1;
                                else current[i][j] = 3;
                            else if (x[i][j] == 0)
                                if (x[j][i] == 1) continue out;
                                else if (x[j][i] == 0) current[j][i] = 3;
                                else current[j][i] = 3;
                            else
                                if (x[j][i] == 1) current[i][j] = 3;
                                else if (x[j][i] == 0) current[j][i] = 3;
                                else current[i][j] = 3;
                        } else {
                            if (x[i][j] == 1)
                                if (x[j][i] == 1) current[i][j] = current[j][i] = 1;
                                else if (x[j][i] == 0) current[i][j] = 3;
                                else current[i][j] = 3;
                            else if (x[i][j] == 0)
                                if (x[j][i] == 1) current[j][i] = 3;
                                else if (x[j][i] == 0) continue out;
                                else current[j][i] = 3;
                            else
                                if (x[j][i] == 1) current[j][i] = 3;
                                else if (x[j][i] == 0) current[i][j] = 3;
                                else current[i][j] = current[j][i] = 1;
                        }
                    }
                }
                solution = current;
                break;
            }

            if (solution == null) {
                out.println("Impossible");
            } else {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        out.print(solution[i][j]);
                        out.print(' ');
                    }
                    out.println();
                }
            }
        };
    }

    public static void main(String[] args) throws Exception {
        new FootballAndLieBundle().run(new FootballAndLie());
    }
}
