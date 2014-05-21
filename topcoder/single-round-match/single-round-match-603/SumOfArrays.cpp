#include <algorithm>
#include <complex>
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

const int N = 1 << 18;
const int LIMIT = 10;
const double PI = acos(-1.0);

typedef std::complex <double> Complex;

int a[N], b[N], count[N];
Complex aa[N], bb[N], cc[N];

struct SumOfArrays {
    string findbestpair(int n, vector <int> aSeed, vector <int> bSeed) {
        generate(n, aSeed, a);
        generate(n, bSeed, b);
        memset(count, 0, sizeof(count));
        for (int k = 1; k <= LIMIT; ++ k) {
            for (int i = 0; i < N; ++ i) {
                aa[i] = a[i] >= k;
                bb[i] = b[i] >= k;
            }
            FFT(aa, N, 1);
            FFT(bb, N, 1);
            for (int i = 0; i < N; ++ i) {
                cc[i] = aa[i] * bb[i];
            }
            FFT(cc, N, -1);
            for (int i = 0; i < N; ++ i) {
                count[i] += (int)(cc[i].real() / N + 0.5);
            }
        }
        std::vector <int> indices;
        for (int j = 0; j < N; ++ j) {
            if (b[j] > LIMIT) {
                indices.push_back(j);
            }
        }
        for (int i = 0; i < N; ++ i) {
            if (a[i] > LIMIT) {
                foreach (it, indices) {
                    int j = *it;
                    count[i + j] += std::min(a[i], b[j]) - LIMIT;
                }
            }
        }
        std::pair <int, int> result;
        for (int i = N - 1; i >= 0; -- i) {
            result = std::max(result, std::make_pair(count[i], i));
        }
        char buffer[22];
        sprintf(buffer, "%d %d", result.first, result.second);
        return buffer;
    }

    void generate(int n, std::vector <int> a, int *count) {
        std::fill(count, count + N, 0);
        int p = a[0];
        int q = a[1];
        for (int i = 0; i < n; ++ i) {
            count[p] ++;
            p = ((long long)q * a[2] + (long long)p * a[3] + a[4]) % a[5];
            std::swap(p, q);
        }
    }

    void FFT(Complex P[], int n, int oper)
    {
        for (int i = 1, j = 0; i < n - 1; i++) {
            for (int s = n; j ^= s >>= 1, ~j & s;);
            if (i < j) {
                swap(P[i], P[j]);
            }
        }
        Complex unit_p0;
        for (int d = 0; (1 << d) < n; d++) {
            int m = 1 << d, m2 = m * 2;
            double p0 = PI / m * oper;
            unit_p0 = Complex(cos(p0), sin(p0));
            for (int i = 0; i < n; i += m2) {
                Complex unit = 1;
                for (int j = 0; j < m; j++) {
                    Complex &P1 = P[i + j + m], &P2 = P[i + j];
                    Complex t = unit * P1;
                    P1 = P2 - t;
                    P2 = P2 + t;
                    unit = unit * unit_p0;
                }
            }
        }
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
            int n                     = 3;
            int Aseed[]               = {1,1,1,1,1,2};
            int Bseed[]               = {1,1,1,1,1,2};
            string expected__         = "3 2";

            clock_t start__           = clock();
            string received__         = SumOfArrays().findbestpair(n, vector <int>(Aseed, Aseed + (sizeof Aseed / sizeof Aseed[0])), vector <int>(Bseed, Bseed + (sizeof Bseed / sizeof Bseed[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int n                     = 3;
            int Aseed[]               = {1,1,1,1,1,4};
            int Bseed[]               = {1,1,1,1,1,4};
            string expected__         = "2 4";

            clock_t start__           = clock();
            string received__         = SumOfArrays().findbestpair(n, vector <int>(Aseed, Aseed + (sizeof Aseed / sizeof Aseed[0])), vector <int>(Bseed, Bseed + (sizeof Bseed / sizeof Bseed[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int n                     = 3;
            int Aseed[]               = {1,2,0,0,1,5};
            int Bseed[]               = {0,1,0,0,1,5};
            string expected__         = "3 2";

            clock_t start__           = clock();
            string received__         = SumOfArrays().findbestpair(n, vector <int>(Aseed, Aseed + (sizeof Aseed / sizeof Aseed[0])), vector <int>(Bseed, Bseed + (sizeof Bseed / sizeof Bseed[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int n                     = 14;
            int Aseed[]               = {5,6,2,4,6,11};
            int Bseed[]               = {6,5,2,4,2,7};
            string expected__         = "9 7";

            clock_t start__           = clock();
            string received__         = SumOfArrays().findbestpair(n, vector <int>(Aseed, Aseed + (sizeof Aseed / sizeof Aseed[0])), vector <int>(Bseed, Bseed + (sizeof Bseed / sizeof Bseed[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int n                     = 100000;
            int Aseed[]               = {6274,99908,61138,86540,56297,100000};
            int Bseed[]               = {28275,25494,65423,61118,64925,100000};
            string expected__         = "3027 102148";

            clock_t start__           = clock();
            string received__         = SumOfArrays().findbestpair(n, vector <int>(Aseed, Aseed + (sizeof Aseed / sizeof Aseed[0])), vector <int>(Bseed, Bseed + (sizeof Bseed / sizeof Bseed[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 5: {
            int n                     = ;
            int Aseed[]               = ;
            int Bseed[]               = ;
            string expected__         = ;

            clock_t start__           = clock();
            string received__         = SumOfArrays().findbestpair(n, vector <int>(Aseed, Aseed + (sizeof Aseed / sizeof Aseed[0])), vector <int>(Bseed, Bseed + (sizeof Bseed / sizeof Bseed[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            int n                     = ;
            int Aseed[]               = ;
            int Bseed[]               = ;
            string expected__         = ;

            clock_t start__           = clock();
            string received__         = SumOfArrays().findbestpair(n, vector <int>(Aseed, Aseed + (sizeof Aseed / sizeof Aseed[0])), vector <int>(Bseed, Bseed + (sizeof Bseed / sizeof Bseed[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int n                     = ;
            int Aseed[]               = ;
            int Bseed[]               = ;
            string expected__         = ;

            clock_t start__           = clock();
            string received__         = SumOfArrays().findbestpair(n, vector <int>(Aseed, Aseed + (sizeof Aseed / sizeof Aseed[0])), vector <int>(Bseed, Bseed + (sizeof Bseed / sizeof Bseed[0])));
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
