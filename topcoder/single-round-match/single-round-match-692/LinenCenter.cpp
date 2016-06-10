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

struct LinenCenter
{
    static const int MOD = (int)1e9 + 9;

    void update(int& x, int a)
    {
        x += a;
        if (x >= MOD) {
            x -= MOD;
        }
    }

    typedef std::vector<int> Polynormial;

    Polynormial multiply(const Polynormial& a, const Polynormial& b)
    {
        Polynormial c(n + 1);
        for (int i = 0; i <= n; ++ i) {
            for (int j = 0; i + j <= n; ++ j) {
                update(c[i + j], (long long)a[i] * b[j] % MOD);
            }
        }
        return c;
    }

    Polynormial power(Polynormial a, int m)
    {
        Polynormial result(n + 1);
        result[0] = 1;
        while (m) {
            if (m & 1) {
                result = multiply(result, a);
            }
            a = multiply(a, a);
            m >>= 1;
        }
        return result;
    }

    int countStrings(string s, int n, int k)
    {
        this->n = n;
        int l = s.size();
        std::vector<int> fail(l + 1);
        std::vector<std::vector<int>> transfer(l + 1, std::vector<int>(26));
        fail[1] = 0;
        transfer[0][s[0] - 'a'] = 1;
        for (int i = 1; i <= l; ++ i) {
            transfer[i] = transfer[fail[i]];
            if (i < l) {
                int c = s[i] - 'a';
                fail[i + 1] = transfer[i][c];
                transfer[i][c] = i + 1;
            }
        }
        std::vector<std::vector<int>> ways(n + 1, std::vector<int>(l + 1));
        ways[0][0] = 1;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < l; ++ j) {
                for (int c = 0; c < 26; ++ c) {
                    update(ways[i + 1][transfer[j][c]], ways[i][j]);
                }
            }
        }
        Polynormial base(n + 1);
        for (int j = 0; j < l; ++ j) {
            int p = j;
            for (int k = 0; k < l - 1 && p != l; ++ k) {
                p = transfer[p][s[k] - 'a'];
            }
            if (p != l) {
                for (int i = 0; i <= n; ++ i) {
                    update(base[i], ways[i][j]);
                }
            }
        }
        base = power(base, k);
        int result = 0;
        for (int i = 0; i <= n; ++ i) {
            int sum = 0;
            for (int j = 0; j < l; ++ j) {
                update(sum, ways[i][j]);
            }
            for (int j = 0; i + j <= n; ++ j) {
                update(result, (long long)sum * base[j] % MOD);
            }
        }
        return result;
    }

    int n;
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

    int verify_case(int casenum, const int &expected, const int &received, clock_t elapsed) {
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
            string S                  = "xy";
            int N                     = 2;
            int K                     = 1;
            int expected__            = 2079;

            clock_t start__           = clock();
            int received__            = LinenCenter().countStrings(S, N, K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            string S                  = "q";
            int N                     = 2;
            int K                     = 1;
            int expected__            = 1926;

            clock_t start__           = clock();
            int received__            = LinenCenter().countStrings(S, N, K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            string S                  = "ababab";
            int N                     = 5;
            int K                     = 4;
            int expected__            = 527166180;

            clock_t start__           = clock();
            int received__            = LinenCenter().countStrings(S, N, K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            string S                  = "fgcdx";
            int N                     = 500;
            int K                     = 10;
            int expected__            = 942389748;

            clock_t start__           = clock();
            int received__            = LinenCenter().countStrings(S, N, K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            string S                  = "ghjhhhgjhjhgjhghghjhjg";
            int N                     = 8;
            int K                     = 10000000;
            int expected__            = 618639712;

            clock_t start__           = clock();
            int received__            = LinenCenter().countStrings(S, N, K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            string S                  = "pdpfrpfdfdpfdpfdpfpdfldfpfldpfdlfpdlfdpflepflfldpflofpwpldlfpde";
            int N                     = 999;
            int K                     = 500000000;
            int expected__            = 852730493;

            clock_t start__           = clock();
            int received__            = LinenCenter().countStrings(S, N, K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 6: {
            string S                  = "a";
            int N                     = 100;
            int K                     = 0;
            int expected__            = 990669582;

            clock_t start__           = clock();
            int received__            = LinenCenter().countStrings(S, N, K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 7: {
            string S                  = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
            int N                     = 1000;
            int K                     = 1000000000;
            int expected__            = 286425258;

            clock_t start__           = clock();
            int received__            = LinenCenter().countStrings(S, N, K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 8: {
            string S                  = ;
            int N                     = ;
            int K                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = LinenCenter().countStrings(S, N, K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 9: {
            string S                  = ;
            int N                     = ;
            int K                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = LinenCenter().countStrings(S, N, K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 10: {
            string S                  = ;
            int N                     = ;
            int K                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = LinenCenter().countStrings(S, N, K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
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
