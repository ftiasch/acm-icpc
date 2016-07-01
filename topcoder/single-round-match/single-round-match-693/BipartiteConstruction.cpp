#include <cstdio>
#include <cstring>
#include <iostream>
#include <functional>
#include <string>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

using std::cerr;
using std::endl;
using std::string;
using std::vector;

struct BipartiteConstruction
{
    static const int N = 20;

    void add(std::vector<int>& result, int a, int b)
    {
        result.push_back(a * N + b);
    }

    std::vector<int> construct(int matches)
    {
        std::vector<int> result;
        result.push_back(20);
        for (int i = 0; i < N; ++ i) {
            if (i) {
                add(result, i, i - 1);
            }
            if (i < N - 1) {
                for (int _ = 0; _ < 3; ++ _) {
                    add(result, i, i);
                }
            }
        }
        for (int i = 0; i < N; ++ i) {
            for (int _ = 0; _ < matches % 3; ++ _) {
                add(result, i, N - 1);
            }
            matches /= 3;
        }
        return result;
    }
};

// BEGIN CUT HERE
//{{{ TESTCODE
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

    template<typename T> std::ostream& operator<<(std::ostream &os, const vector<T> &v) { os << "{"; for (typename vector<T>::const_iterator vi=v.begin(); vi!=v.end(); ++vi) { if (vi != v.begin()) os << ","; os << " " << *vi; } os << " }"; return os; }

    int verify_case(int casenum, const vector <int> &expected, const vector <int> &received, clock_t elapsed) {
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
            int K                     = 2;
            int expected__[]          = {3, 8, 1, 7, 4, 3, 0 };

            clock_t start__           = clock();
            vector <int> received__   = BipartiteConstruction().construct(K);
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 1: {
            int K                     = 5;
            int expected__[]          = {1, 0, 0, 0, 0, 0 };

            clock_t start__           = clock();
            vector <int> received__   = BipartiteConstruction().construct(K);
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 2: {
            int K                     = 0;
            int expected__[]          = {2, 1, 0 };

            clock_t start__           = clock();
            vector <int> received__   = BipartiteConstruction().construct(K);
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 3: {
            int K                     = 2333333;
            int expected__[]          = {20, 93, 195, 195, 211, 87, 114, 369, 390, 216, 195, 305, 17, 370, 356, 308, 150, 263, 20, 153, 331, 79, 344, 108, 114, 257, 245, 289, 211, 388, 388, 359, 293, 263, 219, 131, 201, 293, 279, 204, 97, 367, 90, 279, 308, 324, 323, 359, 338, 63, 26, 318, 218, 226, 164, 361, 276, 388, 343, 149, 17, 336, 88, 161, 76, 237, 136, 278, 302, 107, 271, 15, 382, 45, 338, 264, 78, 150, 220, 341, 180, 20, 252, 94, 114, 161 };

            clock_t start__           = clock();
            vector <int> received__   = BipartiteConstruction().construct(K);
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }

        // custom cases

/*      case 4: {
            int K                     = ;
            int expected__[]          = ;

            clock_t start__           = clock();
            vector <int> received__   = BipartiteConstruction().construct(K);
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }*/
/*      case 5: {
            int K                     = ;
            int expected__[]          = ;

            clock_t start__           = clock();
            vector <int> received__   = BipartiteConstruction().construct(K);
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }*/
/*      case 6: {
            int K                     = ;
            int expected__[]          = ;

            clock_t start__           = clock();
            vector <int> received__   = BipartiteConstruction().construct(K);
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }*/
        default:
            return -1;
        }
    }
}

//}}}

//{{{ DEFAULTMAIN
int main(int argc, char *argv[]) {
    if (argc == 1) {
        moj_harness::run_test();
    } else {
        for (int i=1; i<argc; ++i)
            moj_harness::run_test(atoi(argv[i]));
    }
}
//}}}
// END CUT HERE
