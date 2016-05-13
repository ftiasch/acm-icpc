#include <algorithm>
#include <cmath>
#include <cstdio>
#include <cstring>
#include <complex>
#include <iostream>
#include <functional>
#include <string>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

using std::cerr;
using std::endl;
using std::string;
using std::vector;

const int N = 15;
const int M = 16;

const double PI = acos(-1.);

typedef std::complex<double> Complex;

void FFT(Complex P[], int n, int oper)
{
    for (int i = 1, j = 0; i < n - 1; i++) {
        for (int s = n; j ^= s >>= 1, ~j & s;);
        if (i < j) {
            std::swap(P[i], P[j]);
        }
    }
    for (int d = 0; (1 << d) < n; d++) {
        int m = 1 << d, m2 = m * 2;
        double p0 = PI / m * oper;
        Complex unit_p0(cos(p0), sin(p0));
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

int n;
double dp[1 << M][N];
std::vector<int> value;
std::vector<Complex> cache[N][M];

void solve(int l, int r)
{
    if (r - l == 1) {
        std::vector<double> tmp(dp[l], dp[l] + n);
        for (int i = 0; i < n; ++ i) {
            dp[l][i] = 0.;
            for (int j = 0; j < n; ++ j) {
                if (i != j) {
                    dp[l][i] = std::max(dp[l][i], tmp[j]);
                }
            }
            dp[l][i] += value[i];
        }
    } else {
        int m = l + r >> 1;
        solve(l, m);
        int ln = r - l;
        int lv = __builtin_ctz(ln) - 1;
        for (int i = 0; i < n; ++ i) {
            std::vector<Complex> tmp(ln);
            for (int j = l; j < m; ++ j) {
                tmp[j - l] = dp[j][i];
            }
            FFT(tmp.data(), ln, 1);
            for (int j = 0; j < ln; ++ j) {
                tmp[j] *= cache[i][lv][j];
            }
            FFT(tmp.data(), ln, -1);
            for (int j = m; j < r; ++ j) {
                dp[j][i] += tmp[j - l].real() / ln;
            }
        }
        solve(m, r);
    }
}

struct VendingMachines
{
    double expectedValue(int tickets, std::vector<int> luck, std::vector<int> value_)
    {
        n = luck.size();
        value = value_;
        int m = 0;
        while (1 << m <= tickets) {
            m ++;
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                std::vector<Complex> tmp(1 << j + 1);
                double p = 1.;
                for (int k = 1; k < 1 << j + 1; ++ k) {
                    double pp = (double)k * k / luck[i] / luck[i];
                    tmp[k] = p * pp;
                    p *= 1. - pp;
                }
                FFT(tmp.data(), 1 << j + 1, 1);
                cache[i][j] = tmp;
            }
        }
        memset(dp, 0., sizeof(dp));
        solve(0, 1 << m);
        double result = 0.;
        for (int i = 0; i < n; ++ i) {
            result = std::max(result, dp[tickets][i] - value[i]);
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

    static const double MAX_DOUBLE_ERROR = 1e-9; static bool topcoder_fequ(double expected, double result) { if (isnan(expected)) { return isnan(result); } else if (isinf(expected)) { if (expected > 0) { return result > 0 && isinf(result); } else { return result < 0 && isinf(result); } } else if (isnan(result) || isinf(result)) { return false; } else if (fabs(result - expected) < MAX_DOUBLE_ERROR) { return true; } else { double mmin = std::min(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); double mmax = std::max(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); return result > mmin && result < mmax; } }
    double moj_relative_error(double expected, double result) { if (isnan(expected) || isinf(expected) || isnan(result) || isinf(result) || expected == 0) return 0; return fabs(result-expected) / fabs(expected); }

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
            int tickets               = 2;
            int luck[]                = {1,1};
            int value[]               = {2,3};
            double expected__         = 5.0;

            clock_t start__           = clock();
            double received__         = VendingMachines().expectedValue(tickets, vector <int>(luck, luck + (sizeof luck / sizeof luck[0])), vector <int>(value, value + (sizeof value / sizeof value[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int tickets               = 3;
            int luck[]                = {1,1,1};
            int value[]               = {2,3,4};
            double expected__         = 11.0;

            clock_t start__           = clock();
            double received__         = VendingMachines().expectedValue(tickets, vector <int>(luck, luck + (sizeof luck / sizeof luck[0])), vector <int>(value, value + (sizeof value / sizeof value[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int tickets               = 100;
            int luck[]                = {1, 1, 1};
            int value[]               = {100,100,100};
            double expected__         = 9999.99999999991;

            clock_t start__           = clock();
            double received__         = VendingMachines().expectedValue(tickets, vector <int>(luck, luck + (sizeof luck / sizeof luck[0])), vector <int>(value, value + (sizeof value / sizeof value[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int tickets               = 4;
            int luck[]                = {2,3};
            int value[]               = {4,5};
            double expected__         = 7.693072702331962;

            clock_t start__           = clock();
            double received__         = VendingMachines().expectedValue(tickets, vector <int>(luck, luck + (sizeof luck / sizeof luck[0])), vector <int>(value, value + (sizeof value / sizeof value[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int tickets               = 11;
            int luck[]                = {2,3,4};
            int value[]               = {55,58,60};
            double expected__         = 292.20826703848974;

            clock_t start__           = clock();
            double received__         = VendingMachines().expectedValue(tickets, vector <int>(luck, luck + (sizeof luck / sizeof luck[0])), vector <int>(value, value + (sizeof value / sizeof value[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            int tickets               = 1001;
            int luck[]                = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int value[]               = {1, 4, 9, 29, 35, 40, 49, 55, 63, 72};
            double expected__         = 12321.244694733025;

            clock_t start__           = clock();
            double received__         = VendingMachines().expectedValue(tickets, vector <int>(luck, luck + (sizeof luck / sizeof luck[0])), vector <int>(value, value + (sizeof value / sizeof value[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 6: {
            int tickets               = 40000;
            int luck[]                = {1000000000, 1};
            int value[]               = {1000000000, 1};
            double expected__         = 21333.928554321847;

            clock_t start__           = clock();
            double received__         = VendingMachines().expectedValue(tickets, vector <int>(luck, luck + (sizeof luck / sizeof luck[0])), vector <int>(value, value + (sizeof value / sizeof value[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

        case 7: {
            int tickets               = 1;
            int luck[]                = {2, 2};
            int value[]               = {1, 1};
            double expected__         = 0.25;

            clock_t start__           = clock();
            double received__         = VendingMachines().expectedValue(tickets, vector <int>(luck, luck + (sizeof luck / sizeof luck[0])), vector <int>(value, value + (sizeof value / sizeof value[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
/*      case 8: {
            int tickets               = ;
            int luck[]                = ;
            int value[]               = ;
            double expected__         = ;

            clock_t start__           = clock();
            double received__         = VendingMachines().expectedValue(tickets, vector <int>(luck, luck + (sizeof luck / sizeof luck[0])), vector <int>(value, value + (sizeof value / sizeof value[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 9: {
            int tickets               = ;
            int luck[]                = ;
            int value[]               = ;
            double expected__         = ;

            clock_t start__           = clock();
            double received__         = VendingMachines().expectedValue(tickets, vector <int>(luck, luck + (sizeof luck / sizeof luck[0])), vector <int>(value, value + (sizeof value / sizeof value[0])));
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
