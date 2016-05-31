#include <algorithm>
#include <cmath>
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

struct Undivisors
{
    int numeric_value(char c)
    {
        if ('1' <= c && c <= '9') {
            return c - '0';
        }
        if ('A' <= c && c <= 'Z') {
            return c - 'A' + 10;
        }
        return c - 'a' + 36;
    }

    bool is_prime(int n)
    {
        for (int i = 2; i * i <= n; ++ i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    int k;
    std::vector<int> prime, exponent, base;

    int get(int mask, int i)
    {
        return mask / base[i] % exponent[i];
    }

    int lcm(int u, int v)
    {
        int result = (u % base[3]) | (v % base[3]);
        for (int i = 0; i < 4; ++ i) {
            result += std::max(get(u, i), get(v, i)) * base[i];
        }
        return result;
    }

    int to_mask(int a)
    {
        int result = 0;
        for (int i = 0; i < k; ++ i) {
            int e = 0;
            for (int x = a; x % prime[i] == 0; x /= prime[i]) {
                e ++;
            }
            result += e * base[i];
        }
        return result;
    }

    double getexp(const std::vector<std::string>& a)
    {
        int n = a.size();
        int m = a.at(0).size();
        int max_a = 64;
        for (int p = 2; p < max_a; ++ p) {
            if (is_prime(p)) {
                prime.push_back(p);
                exponent.push_back(2);
                for (int a = p; (a *= p) < max_a; exponent.back() ++);
            }
        }
        base.resize(k = prime.size());
        base[k - 1] = 1;
        for (int i = k - 1; i >= 1; -- i) {
            base[i - 1] = base[i] * exponent[i];
        }
        int max_mask = base[0] * exponent[0];
        std::vector<int> precomputed_mask(max_a);
        for (int a = 2; a < max_a; ++ a) {
            int i = 0;
            while (a % prime[i]) {
                i ++;
            }
            precomputed_mask[a] = precomputed_mask[a / prime[i]] + base[i];
        }
        std::vector<long long> count(max_mask);
        for (int l = 0; l < n; ++ l) {
            std::vector<int> column_lcm(m);
            for (int r = l; r < n; ++ r) {
                for (int i = 0; i < m; ++ i) {
                    column_lcm[i] = lcm(column_lcm[i], precomputed_mask[numeric_value(a[r][i])]);
                }
                for (int i = 0; i < m; ++ i) {
                    int matrix_lcm = 0;
                    for (int j = i; j < m; ++ j) {
                        matrix_lcm = lcm(matrix_lcm, column_lcm[j]);
                        count[matrix_lcm] ++;
                    }
                }
            }
        }
        for (int i = 0; i < k; ++ i) {
            for (int mask = 0; mask < max_mask; ++ mask) {
                if (get(mask, i) > 0) {
                    count[mask] += count[mask - base[i]];
                }
            }
        }
        for (int mask = 0; mask < max_mask; ++ mask) {
            count[mask] *= count[mask];
        }
        for (int i = 0; i < k; ++ i) {
            for (int mask = max_mask - 1; mask >= 0; -- mask) {
                if (get(mask, i) > 0) {
                    count[mask] -= count[mask - base[i]];
                }
            }
        }
        double result = 0.;
        long long total = n * (n + 1) / 2 * m * (m + 1) / 2;
        total *= total;
        for (int mask = 0; mask < max_mask; ++ mask) {
            int d = 2;
            while (d < 64 && lcm(precomputed_mask[d], mask) == mask) {
                d ++;
            }
            result += (double)d * count[mask] / total;
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
            string a[]                = {"11"
,"11"};
            double expected__         = 2.0;

            clock_t start__           = clock();
            double received__         = Undivisors().getexp(vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            string a[]                = {"234"};
            double expected__         = 4.5;

            clock_t start__           = clock();
            double received__         = Undivisors().getexp(vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            string a[]                = {"4356"};
            double expected__         = 5.4;

            clock_t start__           = clock();
            double received__         = Undivisors().getexp(vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            string a[]                = {"12"
,"11"};
            double expected__         = 2.691358024691358;

            clock_t start__           = clock();
            double received__         = Undivisors().getexp(vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            string a[]                = {"2345"
,"AEa9"};
            double expected__         = 6.34;

            clock_t start__           = clock();
            double received__         = Undivisors().getexp(vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            string a[]                = {"ABC2DE"
,"abc3de"};
            double expected__         = 6.668430335097002;

            clock_t start__           = clock();
            double received__         = Undivisors().getexp(vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 6: {
            string a[]                = {"ztTxP64OvgP",
"MYp2q3YQwS1",
"9BwRVK4SvFL",
"VQHojP7HyjF",
"Il4ZCEs7vxP",
"dEvUxcOqw9v",
"f5wmo9OigOD",
"CUhrwte35Af",
"LVn1kAmNgOU",
"bhVmE2bgzHo",
"1NPp2dXsc4g",
"LqUEuGQmJfK",
"ewihrw9MHPQ",
"T7Ru3udY5IK"};
            double expected__         = 21.728568950690164;

            clock_t start__           = clock();
            double received__         = Undivisors().getexp(vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 7: {
            string a[]                = ;
            double expected__         = ;

            clock_t start__           = clock();
            double received__         = Undivisors().getexp(vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            string a[]                = ;
            double expected__         = ;

            clock_t start__           = clock();
            double received__         = Undivisors().getexp(vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 9: {
            string a[]                = ;
            double expected__         = ;

            clock_t start__           = clock();
            double received__         = Undivisors().getexp(vector <string>(a, a + (sizeof a / sizeof a[0])));
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
