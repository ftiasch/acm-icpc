#include <algorithm>
#include <cassert>
#include <climits>
#include <cmath>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <deque>
#include <iostream>
#include <list>
#include <map>
#include <unordered_map>
#include <queue>
#include <set>
#include <string>
#include <utility>
#include <vector>

#define ALL(v) (v).begin(), (v).end()
#define SIZE(v) (int)((v).size())
#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

using std::cerr;
using std::endl;
using std::vector;
using std::string;

struct ThreeLLogo {
    typedef long long Long;

    long long countWays(std::vector <std::string> grid) {
        int n = SIZE(grid);
        int m = SIZE(grid[0]);
        std::unordered_map<Long, Long> states;
        states[0] = 1;
        for (int i = n - 1; i >= 0; -- i) {
            for (int j = 0; j < m; ++ j) {
                std::unordered_map <Long, Long> new_states;
                for (auto iterator: states) {
                    Long state = iterator.first;
                    Long ways = iterator.second;
                    int count = state >> m + 1;
                    int right = state >> m & 1;
                    if (!j && right) {
                        continue;
                    }
                    int up = state >> j & 1;
                    if (right && up) {
                        continue;
                    }
                    if (grid[i][j] == '#') {
                        if (!right && !up) {
                            new_states[state] += ways;
                        }
                    } else {
                        new_states[state] += ways;
                        if (right) {
                            new_states[state & ~(1LL << m)] += ways;
                        } else if (up) {
                            new_states[state & ~(1LL << j)] += ways;
                        } else if (count + 1 <= 3) {
                            new_states[state ^ (count ^ count + 1LL) << m + 1 | 1LL << m | 1LL << j] += ways;
                        }
                    }
                }
                states = new_states;
            }
        }
        return states[3LL << m + 1];
    }
};

// BEGIN CUT HERE
namespace moj_harness {
    int run_test_case(int);
    void run_test(int casenum = -1, bool quiet = false) {
        if (casenum != -1) {
            if (run_test_case(casenum) == -1 && !quiet) {
                cerr << "Illegal input! Test case " << casenum << " does not exist." << endl;
            }
            return;
        }

        int correct = 0, total = 0;
        for (int i=0;; ++i) {
            int x = run_test_case(i);
            if (x == -1) {
                if (i >= 100) break;
                continue;
            }
            correct += x;
            ++total;
        }

        if (total == 0) {
            cerr << "No test cases run." << endl;
        } else if (correct < total) {
            cerr << "Some cases FAILED (passed " << correct << " of " << total << ")." << endl;
        } else {
            cerr << "All " << total << " tests passed!" << endl;
        }
    }

    int verify_case(int casenum, const long long &expected, const long long &received, clock_t elapsed) {
        cerr << "Example " << casenum << "... ";

        string verdict;
        vector<string> info;
        char buf[100];

        if (elapsed > CLOCKS_PER_SEC / 200) {
            sprintf(buf, "time %.2fs", elapsed * (1.0/CLOCKS_PER_SEC));
            info.push_back(buf);
        }

        if (expected == received) {
            verdict = "PASSED";
        } else {
            verdict = "FAILED";
        }

        cerr << verdict;
        if (!info.empty()) {
            cerr << " (";
            for (int i=0; i<(int)info.size(); ++i) {
                if (i > 0) cerr << ", ";
                cerr << info[i];
            }
            cerr << ")";
        }
        cerr << endl;

        if (verdict == "FAILED") {
            cerr << "    Expected: " << expected << endl;
            cerr << "    Received: " << received << endl;
        }

        return verdict == "PASSED";
    }

    int run_test_case(int casenum__) {
        switch (casenum__) {
        case 0: {
            string grid[]             = {"......",
 "......"};
            long long expected__      = 1;

            clock_t start__           = clock();
            long long received__      = ThreeLLogo().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            string grid[]             = {".##..",
 ".....",
 ".#.#.",
 "#...#"};
            long long expected__      = 3;

            clock_t start__           = clock();
            long long received__      = ThreeLLogo().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            string grid[]             = {"....",
 "#...",
 "....",
 "..#."};
            long long expected__      = 4;

            clock_t start__           = clock();
            long long received__      = ThreeLLogo().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            string grid[]             = {"..",
 ".."};
            long long expected__      = 0;

            clock_t start__           = clock();
            long long received__      = ThreeLLogo().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            string grid[]             = {".#.#",
 "....",
 ".#.#",
 "...."};
            long long expected__      = 12;

            clock_t start__           = clock();
            long long received__      = ThreeLLogo().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            string grid[]             = {"######.#######",
 "######.#######",
 "#.####.#######",
 "#.####.#######",
 "#.####........",
 "#.############",
 "#.############",
 "#.############",
 "#.#####.######",
 "#.#####.######",
 "#.#####.######",
 "#....##.######",
 "#######.######",
 "#######.######",
 "#######.######",
 "#######.######",
 "#######.######",
 "#######.######",
 "#######......#",
 "##############"};
            long long expected__      = 37800;

            clock_t start__           = clock();
            long long received__      = ThreeLLogo().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 6: {
            string grid[]             = {"#......",
 ".#....#",
 ".#.#...",
 "#....#.",
 ".##..#.",
 ".#.....",
 ".....#.",
 ".#.#...",
 ".#...#.",
 "..##..."};
            long long expected__      = 27750;

            clock_t start__           = clock();
            long long received__      = ThreeLLogo().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 7: {
            string grid[]             = {"...#..........................",
 "............#..#........#.....",
 ".#............................",
 "...#..........#...#..##.......",
 ".........#....#...#...#.....#.",
 "#...#......#...........#..#...",
 "#...##..#..#..#.....#.........",
 ".............##...............",
 ".....#..#..#..#...............",
 "......#................#......",
 "...#....#.........#.....#...#.",
 ".........#....................",
 ".........#.........##......#..",
 ".#.#..#.....#.#....#.#........",
 "....#.........#...#...........",
 "..##.....#.............#......",
 ".#............................",
 "......................#.......",
 "......#..............#.......#",
 "##..#.........#........#.....#",
 "......#.......#.....#.........",
 ".....#...#...#.#............#.",
 "......#..##....#..............",
 "....#....................#....",
 ".#..........................#.",
 ".............#..........#.....",
 ".#.........#..................",
 "................#.........##..",
 ".......................#.....#",
 ".............#...............#"};
            long long expected__      = 2178497179123LL;

            clock_t start__           = clock();
            long long received__      = ThreeLLogo().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 8: {
            string grid[]             = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = ThreeLLogo().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 9: {
            string grid[]             = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = ThreeLLogo().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 10: {
            string grid[]             = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = ThreeLLogo().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
        default:
            return -1;
        }
    }
}


int main(int argc, char *argv[]) {
    if (argc == 1) {
        moj_harness::run_test();
    } else {
        for (int i=1; i<argc; ++i)
            moj_harness::run_test(atoi(argv[i]));
    }
}
// END CUT HERE
