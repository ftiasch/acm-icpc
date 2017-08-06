#include <algorithm>
#include <string>
#include <vector>

#define DEBUG(...) fprintf(stderr, __VA_ARGS__)

const int N = 50;

struct CoprimeMatrix {
    bool is_prime(int n) {
        for (int d = 2; d * d <= n; ++ d) {
            if (n % d == 0) {
                return false;
            }
        }
        return true;
    }

    bool check(const std::vector<std::string>& a, int p, int r)
    {
        int n = a.size();
        for (int i = 0; i + p < n; ++ i) {
            if ((a[i][i + p] == 'Y') != (std::__gcd(r + i, p) == 1)) {
                return false;
            }
        }
        return true;
    }

    bool solve(const std::vector<std::string>& a) {
        int n = a.size();
        if (a[0][0] == 'Y') {
            // x[0] = 1
            bool ok = true;
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    ok &= (a[i][j] == 'Y') == (std::__gcd(i + 1, j + 1) == 1);
                }
            }
            return ok;
        }
        for (int i = 0; i < n; ++ i) {
            if (a[i][i] == 'Y') {
                return false;
            }
            for (int j = 0; j < n; ++ j) {
                if (a[i][j] != a[j][i]) {
                    return false;
                }
            }
        }
        std::vector<std::string> b(n, std::string(n, 'Y'));
        for (int i = 0; i < n; ++ i) {
            b[i][i] = 'N';
        }
        for (int p = 2; p < n; ++ p) {
            if (is_prime(p)) {
                int r = 0;
                while (r < p && !check(a, p, r)) {
                    r ++;
                }
                if (r == p) {
                    return false;
                }
                // % p == r
                for (int i = 0; i < n; ++ i) {
                    if (std::__gcd(r + i, p) > 1) {
                        for (int m = p; i + m < n; m += p) {
                            b[i][i + m] = b[i + m][i] = 'N';
                        }
                    }
                }
            }
        }
        return a == b;
    }

    std::string isPossible(const std::vector<std::string>& a) {
        return solve(a) ? "Possible" : "Impossible";
    }
};

// {{{ test
// BEGIN CUT HERE
#include <cstdio>
#include <ctime>
#include <iostream>
#include <string>
#include <vector>
namespace moj_harness {
    using std::string;
    using std::vector;
    int run_test_case(int);
    void run_test(int casenum = -1, bool quiet = false) {
        if (casenum != -1) {
            if (run_test_case(casenum) == -1 && !quiet) {
                std::cerr << "Illegal input! Test case " << casenum << " does not exist." << std::endl;
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
            std::cerr << "No test cases run." << std::endl;
        } else if (correct < total) {
            std::cerr << "Some cases FAILED (passed " << correct << " of " << total << ")." << std::endl;
        } else {
            std::cerr << "All " << total << " tests passed!" << std::endl;
        }
    }

    int verify_case(int casenum, const string &expected, const string &received, std::clock_t elapsed) {
        std::cerr << "Example " << casenum << "... ";

        string verdict;
        vector<string> info;
        char buf[100];

        if (elapsed > CLOCKS_PER_SEC / 200) {
            std::sprintf(buf, "time %.2fs", elapsed * (1.0/CLOCKS_PER_SEC));
            info.push_back(buf);
        }

        if (expected == received) {
            verdict = "PASSED";
        } else {
            verdict = "FAILED";
        }

        std::cerr << verdict;
        if (!info.empty()) {
            std::cerr << " (";
            for (size_t i=0; i<info.size(); ++i) {
                if (i > 0) std::cerr << ", ";
                std::cerr << info[i];
            }
            std::cerr << ")";
        }
        std::cerr << std::endl;

        if (verdict == "FAILED") {
            std::cerr << "    Expected: \"" << expected << "\"" << std::endl;
            std::cerr << "    Received: \"" << received << "\"" << std::endl;
        }

        return verdict == "PASSED";
    }

    int run_test_case(int casenum__) {
        switch (casenum__) {
            case 0: {
                        string coprime[]          = {"NYNYN",
                            "YNYYN",
                            "NYNYN",
                            "YYYNY",
                            "NNNYN"};
                        string expected__         = "Possible";

                        std::clock_t start__      = std::clock();
                        string received__         = CoprimeMatrix().isPossible(vector <string>(coprime, coprime + (sizeof coprime / sizeof coprime[0])));
                        return verify_case(casenum__, expected__, received__, clock()-start__);
                    }
            case 1: {
                        string coprime[]          = {"NY",
                            "NN"};
                        string expected__         = "Impossible";

                        std::clock_t start__      = std::clock();
                        string received__         = CoprimeMatrix().isPossible(vector <string>(coprime, coprime + (sizeof coprime / sizeof coprime[0])));
                        return verify_case(casenum__, expected__, received__, clock()-start__);
                    }
            case 2: {
                        string coprime[]          = {"NYYYYN",
                            "YNYNNN",
                            "YYNYYY",
                            "YNYNYN",
                            "YNYYNY",
                            "NNYNYN"};
                        string expected__         = "Possible";

                        std::clock_t start__      = std::clock();
                        string received__         = CoprimeMatrix().isPossible(vector <string>(coprime, coprime + (sizeof coprime / sizeof coprime[0])));
                        return verify_case(casenum__, expected__, received__, clock()-start__);
                    }
            case 3: {
                        string coprime[]          = {"NN",
                            "NN"};
                        string expected__         = "Impossible";

                        std::clock_t start__      = std::clock();
                        string received__         = CoprimeMatrix().isPossible(vector <string>(coprime, coprime + (sizeof coprime / sizeof coprime[0])));
                        return verify_case(casenum__, expected__, received__, clock()-start__);
                    }

                    // custom cases

                    /*      case 4: {
                            string coprime[]          = ;
                            string expected__         = ;

                            std::clock_t start__      = std::clock();
                            string received__         = CoprimeMatrix().isPossible(vector <string>(coprime, coprime + (sizeof coprime / sizeof coprime[0])));
                            return verify_case(casenum__, expected__, received__, clock()-start__);
                            }*/
                    /*      case 5: {
                            string coprime[]          = ;
                            string expected__         = ;

                            std::clock_t start__      = std::clock();
                            string received__         = CoprimeMatrix().isPossible(vector <string>(coprime, coprime + (sizeof coprime / sizeof coprime[0])));
                            return verify_case(casenum__, expected__, received__, clock()-start__);
                            }*/
                    /*      case 6: {
                            string coprime[]          = ;
                            string expected__         = ;

                            std::clock_t start__      = std::clock();
                            string received__         = CoprimeMatrix().isPossible(vector <string>(coprime, coprime + (sizeof coprime / sizeof coprime[0])));
                            return verify_case(casenum__, expected__, received__, clock()-start__);
                            }*/
            default:
                    return -1;
        }
    }
}

// }}}

// {{{ main
#include <cstdlib>
int main(int argc, char *argv[]) {
    if (argc == 1) {
        moj_harness::run_test();
    } else {
        for (int i=1; i<argc; ++i)
            moj_harness::run_test(std::atoi(argv[i]));
    }
}
// END CUT HERE
// }}}
