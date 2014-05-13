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

struct CandidatesSelection {
    std::string possible(std::vector <std::string> score, std::vector <int> result) {
        int n = SIZE(score);
        for (int i = 0; i < n; ++ i) {
            score[i] += i;
        }
        int m = SIZE(score[0]);
        std::vector <int> indices;
        indices.push_back(0);
        indices.push_back(n);
        while (SIZE(indices) <= n) {
            int before = SIZE(indices);
            for (int j = 0; j < m; ++ j) {
                bool valid = true;
                for (int k = 0; k + 1 < SIZE(indices); ++ k) {
                    for (int i = indices[k]; i + 1 < indices[k + 1]; ++ i) {
                        valid &= score[result[i]][j] <= score[result[i + 1]][j];
                    }
                }
                if (valid) {
                    std::vector <int> new_indices;
                    for (int k = 0; k + 1 < SIZE(indices); ++ k) {
                        new_indices.push_back(indices[k]);
                        for (int i = indices[k]; i + 1 < indices[k + 1]; ++ i) {
                            if (score[result[i]][j] < score[result[i + 1]][j]) {
                                new_indices.push_back(i + 1);
                            }
                        }
                    }
                    new_indices.push_back(n);
                    indices = new_indices;
                }
            }
            if (SIZE(indices) == before) {
                return "Impossible";
            }
        }
        return "Possible";
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
            string score[]            = {"CC", "AA", "BB"};
            int result[]              = {1,2,0};
            string expected__         = "Possible";

            clock_t start__           = clock();
            string received__         = CandidatesSelection().possible(vector <string>(score, score + (sizeof score / sizeof score[0])), vector <int>(result, result + (sizeof result / sizeof result[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            string score[]            = {"BAB", "ABB", "AAB", "ABA"};
            int result[]              = {2,0,1,3};
            string expected__         = "Possible";

            clock_t start__           = clock();
            string received__         = CandidatesSelection().possible(vector <string>(score, score + (sizeof score / sizeof score[0])), vector <int>(result, result + (sizeof result / sizeof result[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            string score[]            = {"BAB", "ABB", "AAB", "ABA"};
            int result[]              = {0, 1, 3, 2};
            string expected__         = "Impossible";

            clock_t start__           = clock();
            string received__         = CandidatesSelection().possible(vector <string>(score, score + (sizeof score / sizeof score[0])), vector <int>(result, result + (sizeof result / sizeof result[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            string score[]            = {"AAA", "ZZZ"};
            int result[]              = {1, 0};
            string expected__         = "Impossible";

            clock_t start__           = clock();
            string received__         = CandidatesSelection().possible(vector <string>(score, score + (sizeof score / sizeof score[0])), vector <int>(result, result + (sizeof result / sizeof result[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            string score[]            = {"ZZZ", "AAA"};
            int result[]              = {0, 1};
            string expected__         = "Possible";

            clock_t start__           = clock();
            string received__         = CandidatesSelection().possible(vector <string>(score, score + (sizeof score / sizeof score[0])), vector <int>(result, result + (sizeof result / sizeof result[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            string score[]            = {"ZYYYYX","YXZYXY","ZZZZXX","XZXYYX","ZZZYYZ","ZZXXYZ","ZYZZXZ","XZYYZX"};
            int result[]              = {3,7,1,0,2,5,6,4};
            string expected__         = "Possible";

            clock_t start__           = clock();
            string received__         = CandidatesSelection().possible(vector <string>(score, score + (sizeof score / sizeof score[0])), vector <int>(result, result + (sizeof result / sizeof result[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 6: {
            string score[]            = ;
            int result[]              = ;
            string expected__         = ;

            clock_t start__           = clock();
            string received__         = CandidatesSelection().possible(vector <string>(score, score + (sizeof score / sizeof score[0])), vector <int>(result, result + (sizeof result / sizeof result[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            string score[]            = ;
            int result[]              = ;
            string expected__         = ;

            clock_t start__           = clock();
            string received__         = CandidatesSelection().possible(vector <string>(score, score + (sizeof score / sizeof score[0])), vector <int>(result, result + (sizeof result / sizeof result[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            string score[]            = ;
            int result[]              = ;
            string expected__         = ;

            clock_t start__           = clock();
            string received__         = CandidatesSelection().possible(vector <string>(score, score + (sizeof score / sizeof score[0])), vector <int>(result, result + (sizeof result / sizeof result[0])));
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
