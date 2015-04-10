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

typedef long long Long;

const int N = 100;

struct Point {
    int x, y;

    Point(int x = 0, int y = 0) : x(x), y(y) {}

    Point& operator -=(const Point &o) {
        x -= o.x;
        y -= o.y;
        return *this;
    }

    int quadrant() const {
        return (y ? y : x) > 0;
    }
};

Point operator -(Point a, const Point &b) {
    return a -= b;
}

Long det(const Point &a, const Point &b) {
    return (Long)a.x * b.y - (Long)a.y * b.x;
}

bool operator < (const Point &a, const Point &b) {
    if (a.quadrant() == b.quadrant()) {
        return det(a, b) > 0;
    }
    return a.quadrant() < b.quadrant();
}

int n, m, go[N][N], turn[N][N], rank[N];
bool valid[N][N];
double p[N], memory[N][N];
Point red[N], blue[N];

double solve(int i, int j) {
    double &ref = memory[i][j];
    if (ref < 0) {
        if (rank[j] == n) {
            ref = valid[i][j] ? p[i] : 0;
        } else {
            ref = 0.;
            if (valid[i][j] && rank[j] < n) {
                ref += solve(j, go[i][j]) * p[i];
            }
            int t = turn[i][j];
            if (rank[t] > rank[i]) {
                ref += solve(i, t) * (rank[j] < n ? (1. - p[j]) : 1.);
            }
        }
    }
    return ref;
}

struct BichromeSky {
    double totallyCovered(vector <int> red_x, vector <int> red_y, vector <int> _p, vector <int> blue_x, vector <int> blue_y) {
        n = SIZE(red_x);
        for (int i = 0; i < n; ++ i) {
            red[i] = Point(red_x[i], red_y[i]);
            p[i] = (double)_p[i] / 1000.;
        }
        std::vector <std::vector <int>> cycle(n);
        for (int i = 0; i < n; ++ i) {
            std::vector <std::pair <Point, int>> events;
            for (int j = 0; j < n; ++ j) {
                if (i != j) {
                    events.push_back({red[j] - red[i], j});
                }
            }
            std::sort(ALL(events));
            for (int j = 0; j < n - 1; ++ j) {
                cycle[i].push_back(events[j].second);
            }
            for (int j = 0; j < n - 1; ++ j) {
                turn[i][cycle[i][j]] = cycle[i][(j + 1) % (n - 1)];
            }
            for (int j = 0; j < n; ++ j) {
                if (i != j) {
                    int p = std::lower_bound(ALL(events), std::make_pair(red[i] - red[j], 0)) - events.begin();
                    go[j][i] = cycle[i][p % (n - 1)];
                }
            }
        }
        m = SIZE(blue_x);
        for (int i = 0; i < m; ++ i) {
            blue[i] = Point(blue_x[i], blue_y[i]);
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if (i != j) {
                    valid[i][j] = true;
                    for (int k = 0; k < m; ++ k) {
                        valid[i][j] &= det(red[j] - red[i], blue[k] - red[i]) > 0;
                    }
                }
            }
        }
        double result = 0.;
        for (int left = 0; left < n; ++ left) {
            rank[left] = n;
            double discard = 1.;
            std::vector <int> part;
            for (int i : cycle[left]) {
                if (red[i].x > red[left].x || red[i].x == red[left].x && red[i].y > red[left].y) {
                    rank[i] = part.size();
                    part.push_back(i);
                } else {
                    discard *= 1 - p[i];
                    rank[i] = n + 1;
                }
            }
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    memory[i][j] = -1;
                }
            }
            for (int i : part) {
                result += discard * solve(left, i);
                discard *= 1 - p[i];
            }
        }
        return result;
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

    static const double MAX_DOUBLE_ERROR = 1e-9; static bool topcoder_fequ(double expected, double result) { if (isnan(expected)) { return isnan(result); } else if (isinf(expected)) { if (expected > 0) { return result > 0 && isinf(result); } else { return result < 0 && isinf(result); } } else if (isnan(result) || isinf(result)) { return false; } else if (std::abs(result - expected) < MAX_DOUBLE_ERROR) { return true; } else { double mmin = std::min(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); double mmax = std::max(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); return result > mmin && result < mmax; } }
    double moj_relative_error(double expected, double result) { if (isnan(expected) || isinf(expected) || isnan(result) || isinf(result) || expected == 0) return 0; return std::abs(result-expected) / std::abs(expected); }

    int verify_case(int casenum, const double &expected, const double &received, clock_t elapsed) {
        cerr << "Example " << casenum << "... ";

        string verdict;
        vector<string> info;
        char buf[100];

        if (elapsed > CLOCKS_PER_SEC / 200) {
            sprintf(buf, "time %.2fs", elapsed * (1.0/CLOCKS_PER_SEC));
            info.push_back(buf);
        }

        if (topcoder_fequ(expected, received)) {
            verdict = "PASSED";
            double rerr = moj_relative_error(expected, received);
            if (rerr > 0) {
                sprintf(buf, "relative error %.3e", rerr);
                info.push_back(buf);
            }
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
            int redX[]                = {3,-3,0};
            int redY[]                = {-1,-1,2};
            int prob[]                = {400,500,600};
            int blueX[]               = {1,0,-1};
            int blueY[]               = {0,1,0};
            double expected__         = 0.12;

            clock_t start__           = clock();
            double received__         = BichromeSky().totallyCovered(vector <int>(redX, redX + (sizeof redX / sizeof redX[0])), vector <int>(redY, redY + (sizeof redY / sizeof redY[0])), vector <int>(prob, prob + (sizeof prob / sizeof prob[0])), vector <int>(blueX, blueX + (sizeof blueX / sizeof blueX[0])), vector <int>(blueY, blueY + (sizeof blueY / sizeof blueY[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int redX[]                = {3,-3,3,-3};
            int redY[]                = {3,3,-3,-3};
            int prob[]                = {200,300,400,500};
            int blueX[]               = {0,1,-1};
            int blueY[]               = {-1,-2,-2};
            double expected__         = 0.088;

            clock_t start__           = clock();
            double received__         = BichromeSky().totallyCovered(vector <int>(redX, redX + (sizeof redX / sizeof redX[0])), vector <int>(redY, redY + (sizeof redY / sizeof redY[0])), vector <int>(prob, prob + (sizeof prob / sizeof prob[0])), vector <int>(blueX, blueX + (sizeof blueX / sizeof blueX[0])), vector <int>(blueY, blueY + (sizeof blueY / sizeof blueY[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int redX[]                = {3,-3,0};
            int redY[]                = {-1,-1,2};
            int prob[]                = {400,500,600};
            int blueX[]               = {1,0,-1,123456};
            int blueY[]               = {0,1,0,-654321};
            double expected__         = 0.0;

            clock_t start__           = clock();
            double received__         = BichromeSky().totallyCovered(vector <int>(redX, redX + (sizeof redX / sizeof redX[0])), vector <int>(redY, redY + (sizeof redY / sizeof redY[0])), vector <int>(prob, prob + (sizeof prob / sizeof prob[0])), vector <int>(blueX, blueX + (sizeof blueX / sizeof blueX[0])), vector <int>(blueY, blueY + (sizeof blueY / sizeof blueY[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int redX[]                = {0,-2,-3,-4,-3,-2,0,2,3,4,3,2};
            int redY[]                = {4,3,2,0,-2,-3,-4,-3,-2,0,2,3};
            int prob[]                = {501,502,503,504,505,506,507,508,509,510,511,512};
            int blueX[]               = {1,-1,-1,1};
            int blueY[]               = {1,1,-1,-1};
            double expected__         = 0.6555037822772468;

            clock_t start__           = clock();
            double received__         = BichromeSky().totallyCovered(vector <int>(redX, redX + (sizeof redX / sizeof redX[0])), vector <int>(redY, redY + (sizeof redY / sizeof redY[0])), vector <int>(prob, prob + (sizeof prob / sizeof prob[0])), vector <int>(blueX, blueX + (sizeof blueX / sizeof blueX[0])), vector <int>(blueY, blueY + (sizeof blueY / sizeof blueY[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int redX[]                = {0,1,-3,3};
            int redY[]                = {0,4,-2,-2};
            int prob[]                = {200,300,400,500};
            int blueX[]               = {0,-1,1};
            int blueY[]               = {1,-1,-1};
            double expected__         = 0.06;

            clock_t start__           = clock();
            double received__         = BichromeSky().totallyCovered(vector <int>(redX, redX + (sizeof redX / sizeof redX[0])), vector <int>(redY, redY + (sizeof redY / sizeof redY[0])), vector <int>(prob, prob + (sizeof prob / sizeof prob[0])), vector <int>(blueX, blueX + (sizeof blueX / sizeof blueX[0])), vector <int>(blueY, blueY + (sizeof blueY / sizeof blueY[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            int redX[]                = {10,-17,12,-11,-13,-10,-15,14,-4,2};
            int redY[]                = {8,17,-13,-19,-14,11,17,8,-8,-15};
            int prob[]                = {412,360,656,876,984,160,368,873,223,128};
            int blueX[]               = {-9,-3,6,-9,-5,4,-3,10,-7,2};
            int blueY[]               = {-6,10,10,-9,-10,-6,2,-10,-9,6};
            double expected__         = 0.34037052019900405;

            clock_t start__           = clock();
            double received__         = BichromeSky().totallyCovered(vector <int>(redX, redX + (sizeof redX / sizeof redX[0])), vector <int>(redY, redY + (sizeof redY / sizeof redY[0])), vector <int>(prob, prob + (sizeof prob / sizeof prob[0])), vector <int>(blueX, blueX + (sizeof blueX / sizeof blueX[0])), vector <int>(blueY, blueY + (sizeof blueY / sizeof blueY[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 6: {
            int redX[]                = ;
            int redY[]                = ;
            int prob[]                = ;
            int blueX[]               = ;
            int blueY[]               = ;
            double expected__         = ;

            clock_t start__           = clock();
            double received__         = BichromeSky().totallyCovered(vector <int>(redX, redX + (sizeof redX / sizeof redX[0])), vector <int>(redY, redY + (sizeof redY / sizeof redY[0])), vector <int>(prob, prob + (sizeof prob / sizeof prob[0])), vector <int>(blueX, blueX + (sizeof blueX / sizeof blueX[0])), vector <int>(blueY, blueY + (sizeof blueY / sizeof blueY[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int redX[]                = ;
            int redY[]                = ;
            int prob[]                = ;
            int blueX[]               = ;
            int blueY[]               = ;
            double expected__         = ;

            clock_t start__           = clock();
            double received__         = BichromeSky().totallyCovered(vector <int>(redX, redX + (sizeof redX / sizeof redX[0])), vector <int>(redY, redY + (sizeof redY / sizeof redY[0])), vector <int>(prob, prob + (sizeof prob / sizeof prob[0])), vector <int>(blueX, blueX + (sizeof blueX / sizeof blueX[0])), vector <int>(blueY, blueY + (sizeof blueY / sizeof blueY[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            int redX[]                = ;
            int redY[]                = ;
            int prob[]                = ;
            int blueX[]               = ;
            int blueY[]               = ;
            double expected__         = ;

            clock_t start__           = clock();
            double received__         = BichromeSky().totallyCovered(vector <int>(redX, redX + (sizeof redX / sizeof redX[0])), vector <int>(redY, redY + (sizeof redY / sizeof redY[0])), vector <int>(prob, prob + (sizeof prob / sizeof prob[0])), vector <int>(blueX, blueX + (sizeof blueX / sizeof blueX[0])), vector <int>(blueY, blueY + (sizeof blueY / sizeof blueY[0])));
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
