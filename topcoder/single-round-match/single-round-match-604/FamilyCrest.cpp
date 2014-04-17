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

int gcd(int a, int b) {
    return b ? gcd(b, a % b) : a;
}

struct Point {
    int x, y;

    Point(int x, int y) : x(x), y(y) {}

    Point &operator -=(const Point &o) {
        x -= o.x, y -= o.y;
        return *this;
    }

    int quadrant() const {
        if (y) {
            return y > 0;
        }
        return x > 0;
    }

    Point normalize() const {
        assert(x || y);
        int g = std::abs(gcd(x, y));
        return Point(x / g, y / g);
    }

    Point negate() const {
        return Point(-x, -y);
    }

    bool on(const Point&, const Point&) const;
};

int det(const Point &a, const Point &b) {
    return a.x * b.y - a.y * b.x;
}

int dot(const Point &a, const Point &b) {
    return a.x * b.x + a.y * b.y;
}

bool operator <(const Point &a, const Point &b) {
    if (a.quadrant() != b.quadrant()) {
        return a.quadrant() < b.quadrant();
    }
    return det(a, b) > 0;
}

bool operator ==(const Point &a, const Point &b) {
    return a.x == b.x && a.y == b.y;
}

Point operator -(Point a, const Point &b) {
    return a -= b;
}

bool Point::on(const Point &a, const Point &b) const {
    const Point &p = *this;
    return (long long)det(a - p, b - p) == 0 && (long long)dot(a - p, b - p) < 0;
}

typedef std::pair <Point, Point> Segment;

bool isStrictlyCross(const Segment &u, const Segment &v) {
    Point n = u.second - u.first;
    return (long long)det(v.first - u.first, n) * det(v.second - u.first, n) < 0;
}

bool isStrictlyIntersect(const Segment &u, const Segment &v) {
    return isStrictlyCross(u, v) && isStrictlyCross(v, u);
}

struct FamilyCrest {
    string canBeInfinite(vector <int> a, vector <int> b, vector <int> c, vector <int> d) {
        std::vector <Segment> segments;
        int n = SIZE(a);
        for (int i = 0; i < n; ++ i) {
            segments.push_back(std::make_pair(Point(a[i], b[i]), Point(c[i], d[i])));
        }
        return canBeInfinite(segments) ? "Infinite" : "Finite";
    }

    void add(std::vector <std::pair <Point, int>> &events, const Point &a, const Point &b) {
//printf("(%d, %d), (%d, %d)\n", a.x, a.y, b.x, b.y);
#define ADD(a, v) events.push_back(std::make_pair(a, v)) //; printf("+ (%d, %d) %d\n", a.x, a.y, v)
        if (a < b) {
            ADD(a, +1);
            ADD(b, -1);
        } else {
            ADD(Point(-1, 0), +1);
            ADD(a, +1);
            ADD(b, -1);
        }
#undef ADD
    }

    bool canBeInfinite(std::vector <Segment> segments) {
        int n = SIZE(segments);
        for (int j = 0; j < n; ++ j) {
            for (int i = 0; i < j; ++ i) {
                if (isStrictlyIntersect(segments[i], segments[j])) {
                    return false;
                }
            }
        }
        std::vector <std::pair <Point, int>> events;
        bool found = false;
#define END(i, j) ((j) ? segments[i].second : segments[i].first)
        for (int _ = 0; _ < n; ++ _) {
            for (int __ = 0; __ < 2; ++ __) {
                const Point &p = END(_, __);
                std::vector <Point> edges;
                for (int i = 0; i < n; ++ i) {
                    bool contains = p.on(segments[i].first, segments[i].second);
                    for (int j = 0; j < 2; ++ j) {
                        if (contains || END(i, j) == p) {
                            edges.push_back((END(i, j ^ 1) - p).normalize());
                        }
                    }
                }
                std::sort(ALL(edges));
                edges.erase(std::unique(ALL(edges)), edges.end());
                if (SIZE(edges) > 2) {
                    return false;
                }
                if (SIZE(edges) < 2) {
                    continue;
                }
                if (SIZE(edges) == 2) {
                    Point a = edges[0];
                    Point b = edges[1];
                    if (det(a, b) == 0) {
                        continue;
                    }
                    if (det(a, b) < 0) {
                        std::swap(a, b);
                    }
                    found = true;
                    add(events, b, a.negate());
                    add(events, b.negate(), a);
                }
            }
        }
#undef END
        if (!found) {
            return true;
        }
        events.push_back(std::make_pair(Point(-1, 0), -2));
        std::sort(ALL(events));
        events.push_back(std::make_pair(Point(-1, 0), 0));
//for (auto event : events) {
//    printf("(%d, %d) %d\n", event.first.x, event.first.y, event.second);
//}
        int count = 0;
        for (int i = 1; i < SIZE(events); ++ i) {
            if (!(events[i - 1].first == events[i].first) && !count) {
                return true;
            }
            count += events[i].second;
        }
        return false;
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

    int verify_case(int casenum, const string &expected, const string &received, clock_t elapsed) {
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
            cerr << "    Expected: \"" << expected << "\"" << endl;
            cerr << "    Received: \"" << received << "\"" << endl;
        }

        return verdict == "PASSED";
    }

    int run_test_case(int casenum__) {
        switch (casenum__) {
        case 0: {
            int A[]                   = {0};
            int B[]                   = {0};
            int C[]                   = {0};
            int D[]                   = {1};
            string expected__         = "Infinite";

            clock_t start__           = clock();
            string received__         = FamilyCrest().canBeInfinite(vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])), vector <int>(D, D + (sizeof D / sizeof D[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int A[]                   = {0,1,1,0};
            int B[]                   = {0,0,1,1};
            int C[]                   = {1,1,0,0};
            int D[]                   = {0,1,1,0};
            string expected__         = "Finite";

            clock_t start__           = clock();
            string received__         = FamilyCrest().canBeInfinite(vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])), vector <int>(D, D + (sizeof D / sizeof D[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int A[]                   = {0,1,2,3};
            int B[]                   = {0,3,0,3};
            int C[]                   = {1,2,3,4};
            int D[]                   = {3,0,3,0};
            string expected__         = "Infinite";

            clock_t start__           = clock();
            string received__         = FamilyCrest().canBeInfinite(vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])), vector <int>(D, D + (sizeof D / sizeof D[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int A[]                   = {0,1,2,3,-1,-3,-1};
            int B[]                   = {0,3,0,3,3,2,1};
            int C[]                   = {1,2,3,4,-3,-1,-3};
            int D[]                   = {3,0,3,0,2,1,0};
            string expected__         = "Finite";

            clock_t start__           = clock();
            string received__         = FamilyCrest().canBeInfinite(vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])), vector <int>(D, D + (sizeof D / sizeof D[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int A[]                   = {0,2,4,6,-1,-2,-1};
            int B[]                   = {0,1,0,1,3,2,1};
            int C[]                   = {2,4,6,8,-2,-1,-2};
            int D[]                   = {1,0,1,0,2,1,0};
            string expected__         = "Infinite";

            clock_t start__           = clock();
            string received__         = FamilyCrest().canBeInfinite(vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])), vector <int>(D, D + (sizeof D / sizeof D[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            int A[]                   = {0,0,0,2,3,3,4,5,6,7,9,9,9,9};
            int B[]                   = {0,2,1,0,0,2,0,0,2,0,0,2,1,0};
            int C[]                   = {0,1,1,2,3,4,4,5,8,7,9,10,10,10};
            int D[]                   = {2,2,1,2,2,0,2,2,2,2,2,2,1,0};
            string expected__         = "Finite";

            clock_t start__           = clock();
            string received__         = FamilyCrest().canBeInfinite(vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])), vector <int>(D, D + (sizeof D / sizeof D[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 6: {
            int A[]                   = {-6,-3,3,-6,-3,3};
            int B[]                   = {1,2,-2,-1,-2,2};
            int C[]                   = {-3,3,6,-3,3,6};
            int D[]                   = {2,-2,-1,-2,2,1};
            string expected__         = "Finite";

            clock_t start__           = clock();
            string received__         = FamilyCrest().canBeInfinite(vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])), vector <int>(D, D + (sizeof D / sizeof D[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 7: {
            int A[]                   = {-1,-2,0,1};
            int B[]                   = {0,0,0,0};
            int C[]                   = {1,0,2,-1};
            int D[]                   = {0,0,0,0};
            string expected__         = "Infinite";

            clock_t start__           = clock();
            string received__         = FamilyCrest().canBeInfinite(vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])), vector <int>(D, D + (sizeof D / sizeof D[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

        case 8: {
            int A[]                   = {0, 2, 1, 1};
            int B[]                   = {6, 5, 6, 6};
            int C[]                   = {-5, 1, 0, 2};
            int D[]                   = {0, 6, 6, 5};
            string expected__         = "Infinite";

            clock_t start__           = clock();
            string received__         = FamilyCrest().canBeInfinite(vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])), vector <int>(D, D + (sizeof D / sizeof D[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
/*      case 9: {
            int A[]                   = ;
            int B[]                   = ;
            int C[]                   = ;
            int D[]                   = ;
            string expected__         = ;

            clock_t start__           = clock();
            string received__         = FamilyCrest().canBeInfinite(vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])), vector <int>(D, D + (sizeof D / sizeof D[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 10: {
            int A[]                   = ;
            int B[]                   = ;
            int C[]                   = ;
            int D[]                   = ;
            string expected__         = ;

            clock_t start__           = clock();
            string received__         = FamilyCrest().canBeInfinite(vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])), vector <int>(D, D + (sizeof D / sizeof D[0])));
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
