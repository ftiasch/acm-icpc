import java.math.*;
import java.util.*;

public class FoxTheLinguist {
    final static int INF = (int)1e9;

    public int minimalHours(int n, String[] courseInfo) {
        int v = n * 10;
        int[][] cost = new int[v + 1][v + 1];
        for (int i = 0; i < v + 1; ++ i) {
            for (int j = 0; j < v + 1; ++ j) {
                cost[i][j] = INF;
            }
        }
        for (int i = 0; i < n; ++ i) {
            cost[v][i * 10] = 0;
            for (int j = 1; j < 10; ++ j) {
                cost[i * 10 + j][i * 10 + j - 1] = 0;
            }
        }
        String[] courses = process(courseInfo);
        for (String course : courses) {
            char[] buffer = course.toCharArray();
            int a = (buffer[0] - 'A') * 10 + (buffer[1] - '0');
            int b = (buffer[4] - 'A') * 10 + (buffer[5] - '0');
            int c = Integer.parseInt(course.substring(7));
            cost[a][b] = Math.min(cost[a][b], c);
        }
        return solve(cost, v);
    }

    String[] process(String[] strings) {
        StringBuffer buffer = new StringBuffer("");
        for (String string : strings) {
            buffer.append(string);
        }
        return buffer.toString().split(" ");
    }

    int find(int[] parent, int u) {
        if (parent[u] != u) {
            parent[u] = find(parent, parent[u]);
        }
        return parent[u];
    }

    int solve(int[][] cost, int root) {
        int n = cost.length;
        int[] parent = new int[n];
        for (int u = 0; u < n; ++ u) {
            parent[u] = u;
        }
        int answer = 0;
        while (true) {
            int[] chosen = new int[n];
            Arrays.fill(chosen, -1);
            for (int u = 0; u < n; ++ u) {
                if (find(parent, u) == u && u != find(parent, root)) {
                    for (int v = 0; v < n; ++ v) {
                        if (find(parent, v) == v && (chosen[u] == -1 || cost[v][u] < cost[chosen[u]][u])) {
                            chosen[u] = v;
                        }
                    }
                    if (chosen[u] == -1 || cost[chosen[u]][u] >= INF) {
                        return -1;
                    }
                }
            }
            boolean found = false;
            int [] visit = new int[n];
            Arrays.fill(visit, -1);
            for (int u = 0; u < n && !found; ++ u) {
                if (chosen[u] != -1 && visit[u] == -1) {
                    int v = u;
                    ArrayList <Integer> stack = new ArrayList <Integer>();
                    while (chosen[v] != -1 && visit[v] == -1) {
                        stack.add(v);
                        visit[v] = u;
                        v = chosen[v];
                    }
                    if (chosen[v] != -1 && visit[v] == u) {
                        found = true;
                        ArrayList <Integer> cycle = new ArrayList <Integer>();
                        for (int i = stack.size() - 1; i >= 0; -- i) {
                            int p = stack.get(i);
                            answer += cost[chosen[p]][p];
                            cycle.add(p);
                            if (p == v) {
                                break;
                            }
                        }
                        for (int p : cycle) {
                            parent[p] = v;
                        }
                        for (int p = 0; p < n; ++ p) {
                            if (find(parent, p) == p && find(parent, p) != v) {
                                int tmp = INF;
                                for (int q : cycle) {
                                    if (cost[p][q] < INF) {
                                        tmp = Math.min(tmp, cost[p][q] - cost[chosen[q]][q]);
                                    }
                                    cost[v][p] = Math.min(cost[v][p], cost[q][p]);
                                }
                                cost[p][v] = tmp;
                            }
                        }
                        cost[v][v] = INF;
                    }
                }
            }
//debug(answer); for (int u = 0; u < n; ++ u) for (int v = 0; v < n; ++ v) if (find(parent, u) == u && find(parent, v) == v && cost[u][v] < INF) System.err.println(String.format("%d->%d %d", u, v, cost[u][v]));
            if (!found) {
                for (int u = 0; u < n; ++ u) {
                    if (chosen[u] != -1) {
                        answer += cost[chosen[u]][u];
                    }
                }
                break;
            }
        }
        return answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FoxTheLinguistHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FoxTheLinguistHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FoxTheLinguistHarness {
    public static void run_test(int casenum) {
        if (casenum != -1) {
            if (runTestCase(casenum) == -1)
                System.err.println("Illegal input! Test case " + casenum + " does not exist.");
            return;
        }

        int correct = 0, total = 0;
        for (int i=0;; ++i) {
            int x = runTestCase(i);
            if (x == -1) {
                if (i >= 100) break;
                continue;
            }
            correct += x;
            ++total;
        }

        if (total == 0) {
            System.err.println("No test cases run.");
        } else if (correct < total) {
            System.err.println("Some cases FAILED (passed " + correct + " of " + total + ").");
        } else {
            System.err.println("All " + total + " tests passed!");
        }
    }

    static boolean compareOutput(int expected, int result) { return expected == result; }
    static String formatResult(int res) {
        return String.format("%d", res);
    }

    static int verifyCase(int casenum, int expected, int received) {
        System.err.print("Example " + casenum + "... ");
        if (compareOutput(expected, received)) {
            System.err.println("PASSED");
            return 1;
        } else {
            System.err.println("FAILED");
            System.err.println("    Expected: " + formatResult(expected));
            System.err.println("    Received: " + formatResult(received));
            return 0;
        }
    }

    static int runTestCase(int casenum__) {
        switch(casenum__) {
        case 0: {
            int n                     = 1;
            String[] courseInfo       = {"A0->A9:1000 A0->A6:0300 A3->A9:0600"};
            int expected__            = 900;

            return verifyCase(casenum__, expected__, new FoxTheLinguist().minimalHours(n, courseInfo));
        }
        case 1: {
            int n                     = 2;
            String[] courseInfo       = {"A0->A9:1000 B0->B9:1000 A1->B9:0300 B1->A9:0200"};
            int expected__            = 1200;

            return verifyCase(casenum__, expected__, new FoxTheLinguist().minimalHours(n, courseInfo));
        }
        case 2: {
            int n                     = 3;
            String[] courseInfo       = {"C0->A6:00", "01 A3", "->B9:0001 A3->C6:000", "1",
" C3->A9:0001 A9->C9:0001 A0->A9:9999",
" B0->B9:9999 C0->C9:9999 A6->A9:9999"};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new FoxTheLinguist().minimalHours(n, courseInfo));
        }
        case 3: {
            int n                     = 4;
            String[] courseInfo       = {"A0->A6:6666 A0->A9:9999",
" B0->B6:6666 B0->B9:9999",
" C0->C6:6666 C0->C9:9999",
" D0->D6:6666 D0->D9:9999",
" A6->B6:0666 B6->C6:0666",
" C6->D6:0666 D6->A6:0666",
" A9->B9:0099 B9->C9:0099",
" C9->D9:0099 D9->A9:0099"};
            int expected__            = 10296;

            return verifyCase(casenum__, expected__, new FoxTheLinguist().minimalHours(n, courseInfo));
        }
        case 4: {
            int n                     = 1;
            String[] courseInfo       = {"A0->A9:9999 A0->A9:8888"};
            int expected__            = 8888;

            return verifyCase(casenum__, expected__, new FoxTheLinguist().minimalHours(n, courseInfo));
        }
        case 5: {
            int n                     = 1;
            String[] courseInfo       = {"A9->A9:0000",
" A9->A0:0000"};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new FoxTheLinguist().minimalHours(n, courseInfo));
        }

        // custom cases

      case 6: {
            int n                     = 7;
            String[] courseInfo       = {"C0->A9:0000 A0->B9:0000 A0->C9:0000 G0->D9:0000 E0", "->E9:0000 G0->F9:0000 E0->G9:0000 C8->C8:0460 B4->", "G0:0532 E6->B7:0468 B1->E4:0528 C4->B6:0519 B9->G4", ":0530 G9->G9:0529 A4->F2:0509 D3->C6:0504 D2->C3:0", "508 A9->E4:0455 B5->G2:0488 E4->G2:0473 B9->D9:052", "8 C8->D6:0526 F9->E4:0530 D2->D9:0474 D8->D2:0465 ", "A2->G9:0511 A9->F8:0488 G6->C5:0496 C1->G4:0529 E9", "->E4:0489 G4->D0:0457 F3->B9:0492 B8->E9:0493 A7->", "G7:0484 B8->F1:0517 E4->G5:0471 D9->C3:0508 G3->E6", ":0515 C2->C1:0473 E6->A6:0515 A5->F8:0524 B0->C2:0", "496 G4->A5:0513 G3->G0:0529 D9->A5:0530 B5->B5:046", "5 E0->D9:0502 D8->A4:0474 B9->B3:0463 A7->E4:0465 ", "F2->G1:0491 D0->B3:0477 F7->C8:0472 G4->B1:0502 A6", "->D9:0460 F2->C0:0453 F7->D8:0505 C0->E6:0495 G9->", "E0:0463 E7->B8:0523"};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new FoxTheLinguist().minimalHours(n, courseInfo));
        }
/*      case 7: {
            int n                     = ;
            String[] courseInfo       = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FoxTheLinguist().minimalHours(n, courseInfo));
        }*/
/*      case 8: {
            int n                     = ;
            String[] courseInfo       = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FoxTheLinguist().minimalHours(n, courseInfo));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
