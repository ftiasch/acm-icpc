#include <algorithm>
#include <cassert>
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

const int M = 500;
const int C = 100;

int binom[C * 3 + 1][C * 3 + 1], ways[M + 1][C + 1][C + 1];

struct WinterAndShopping {
    static const int MOD = (int)1e9 + 7;

    int permutations(int red, int green, int blue) {
        return (long long)binom[red + green + blue][red] * binom[green + blue][green] % MOD;
    }

    int getNumber(vector <int> begin, vector <int> red, vector <int> green, vector <int> blue) {
        memset(binom, 0, sizeof(binom));
        for (int i = 0; i <= C * 3; ++ i) {
            binom[i][0] = 1;
            for (int j = 1; j <= i; ++ j) {
                binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
            }
        }
        int n = SIZE(begin);
        int m = 0;
        std::vector <int> length(n);
        std::vector <std::pair <std::pair <int, int>, int> > order;
        for (int i = 0; i < n; ++ i) {
            begin[i] --;
            length[i] = red[i] + green[i] + blue[i];
            m = std::max(m, begin[i] + length[i]);
            order.push_back(std::make_pair(std::make_pair(begin[i], -length[i]), i));
        }
        std::sort(ALL(order));
        std::vector <bool> valid(n, false);
        std::vector <int> current(m + 1, -1);
        for (int i = 0, max = 0; i < n; ++ i) {
            int id = order[i].second;
            if (begin[id] + length[id] <= max) {
                continue;
            }
            max = begin[id] + length[id];
            valid[id] = true;
            for (int j = begin[id]; j < begin[id] + length[id]; ++ j) {
                current[j] = id;
            }
        }
        memset(ways, 0, sizeof(ways));
        ways[0][0][0] = 1;
        for (int i = 0; i <= m; ++ i) {
            int id = i ? current[i - 1] : -1;
            if (id != -1 && begin[id] + length[id] == i) {
                int result = ways[i][red[id]][green[id]];
                for (int r = 0; r <= C; ++ r) {
                    for (int g = 0; g <= C; ++ g) {
                        ways[i][r][g] = 0;
                    }
                }
                ways[i][0][0] = result;
                id = -1;
            }
            if (i < m) {
                int j = 0;
                while (j < n && (valid[j] || begin[j] != i)) {
                    j ++;
                }
                if (j < n) {
                    int coefficient = permutations(red[j], green[j], blue[j]);
                    for (int r = 0; r + red[j] <= C; ++ r) {
                        for (int g = 0; g + green[j] <= C; ++ g) {
                            if (ways[i][r][g]) {
                                (ways[i + length[j]][r + red[j]][g + green[j]] += (long long)coefficient * ways[i][r][g] % MOD) %= MOD;
                            }
                        }
                    }
                } else if (id != -1 && current[i] != -1 && current[i] != id) {
                    int left = begin[id] + length[id] - i;
                    for (int r = 0; r <= red[id]; ++ r) {
                        for (int g = 0; g <= green[id]; ++ g) {
                            if (ways[i][r][g]) {
                                int nr = red[id] - r;
                                int ng = green[id] - g;
                                int nb = left - nr - ng;
                                if (nb >= 0) {
                                    (ways[begin[id] + length[id]][nr][ng] += (long long)permutations(nr, ng, nb) * ways[i][r][g] % MOD) %= MOD;
                                }
                            }
                        }
                    }
                } else {
                    id = current[i];
                    if (id != -1) {
                        for (int r = 0; r <= C; ++ r) {
                            for (int g = 0; g <= C; ++ g) {
                                if (ways[i][r][g]) {
                                    if (r + 1 <= C) {
                                        (ways[i + 1][r + 1][g] += ways[i][r][g]) %= MOD;
                                    }
                                    if (g + 1 <= C) {
                                        (ways[i + 1][r][g + 1] += ways[i][r][g]) %= MOD;
                                    }
                                    (ways[i + 1][r][g] += ways[i][r][g]) %= MOD;
                                }
                            }
                        }
                    } else {
                        for (int r = 0; r <= C; ++ r) {
                            for (int g = 0; g <= C; ++ g) {
                                if (ways[i][r][g]) {
                                    (ways[i + 1][0][0] += ways[i][r][g]) %= MOD;
                                }
                            }
                        }
                    }
                }
            }
        }
        int result = 0;
        for (int r = 0; r <= C; ++ r) {
            for (int g = 0; g <= C; ++ g) {
                (result += ways[m][r][g]) %= MOD;
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
			int first[]               = {1};
			int red[]                 = {1};
			int green[]               = {1};
			int blue[]                = {1};
			int expected__            = 6;

			clock_t start__           = clock();
			int received__            = WinterAndShopping().getNumber(vector <int>(first, first + (sizeof first / sizeof first[0])), vector <int>(red, red + (sizeof red / sizeof red[0])), vector <int>(green, green + (sizeof green / sizeof green[0])), vector <int>(blue, blue + (sizeof blue / sizeof blue[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 1: {
			int first[]               = {1};
			int red[]                 = {3};
			int green[]               = {0};
			int blue[]                = {0};
			int expected__            = 1;

			clock_t start__           = clock();
			int received__            = WinterAndShopping().getNumber(vector <int>(first, first + (sizeof first / sizeof first[0])), vector <int>(red, red + (sizeof red / sizeof red[0])), vector <int>(green, green + (sizeof green / sizeof green[0])), vector <int>(blue, blue + (sizeof blue / sizeof blue[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 2: {
			int first[]               = {1, 2};
			int red[]                 = {1, 1};
			int green[]               = {1, 1};
			int blue[]                = {1, 1};
			int expected__            = 6;

			clock_t start__           = clock();
			int received__            = WinterAndShopping().getNumber(vector <int>(first, first + (sizeof first / sizeof first[0])), vector <int>(red, red + (sizeof red / sizeof red[0])), vector <int>(green, green + (sizeof green / sizeof green[0])), vector <int>(blue, blue + (sizeof blue / sizeof blue[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 3: {
			int first[]               = {47, 47};
			int red[]                 = {1, 0};
			int green[]               = {0, 1};
			int blue[]                = {0, 0};
			int expected__            = 0;

			clock_t start__           = clock();
			int received__            = WinterAndShopping().getNumber(vector <int>(first, first + (sizeof first / sizeof first[0])), vector <int>(red, red + (sizeof red / sizeof red[0])), vector <int>(green, green + (sizeof green / sizeof green[0])), vector <int>(blue, blue + (sizeof blue / sizeof blue[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 4: {
			int first[]               = {1, 100, 200};
			int red[]                 = {100, 1, 0};
			int green[]               = {100, 3, 7};
			int blue[]                = {100, 2, 4};
			int expected__            = 79290907;

			clock_t start__           = clock();
			int received__            = WinterAndShopping().getNumber(vector <int>(first, first + (sizeof first / sizeof first[0])), vector <int>(red, red + (sizeof red / sizeof red[0])), vector <int>(green, green + (sizeof green / sizeof green[0])), vector <int>(blue, blue + (sizeof blue / sizeof blue[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 5: {
			int first[]               = {1, 3};
			int red[]                 = {3, 4};
			int green[]               = {4, 4};
			int blue[]                = {2, 1};
			int expected__            = 840;

			clock_t start__           = clock();
			int received__            = WinterAndShopping().getNumber(vector <int>(first, first + (sizeof first / sizeof first[0])), vector <int>(red, red + (sizeof red / sizeof red[0])), vector <int>(green, green + (sizeof green / sizeof green[0])), vector <int>(blue, blue + (sizeof blue / sizeof blue[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 6: {
			int first[]               = {2, 1};
			int red[]                 = {100, 100};
			int green[]               = {100, 100};
			int blue[]                = {99, 100};
			int expected__            = 412784747;

			clock_t start__           = clock();
			int received__            = WinterAndShopping().getNumber(vector <int>(first, first + (sizeof first / sizeof first[0])), vector <int>(red, red + (sizeof red / sizeof red[0])), vector <int>(green, green + (sizeof green / sizeof green[0])), vector <int>(blue, blue + (sizeof blue / sizeof blue[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 7: {
			int first[]               = {1, 220, 150};
			int red[]                 = {70, 70, 50};
			int green[]               = {70, 70, 50};
			int blue[]                = {70, 70, 50};
			int expected__            = 291495139;

			clock_t start__           = clock();
			int received__            = WinterAndShopping().getNumber(vector <int>(first, first + (sizeof first / sizeof first[0])), vector <int>(red, red + (sizeof red / sizeof red[0])), vector <int>(green, green + (sizeof green / sizeof green[0])), vector <int>(blue, blue + (sizeof blue / sizeof blue[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 8: {
			int first[]               = {2, 2, 70, 159};
			int red[]                 = {100, 20, 21, 52};
			int green[]               = {100, 20, 29, 45};
			int blue[]                = {100, 22, 39, 30};
			int expected__            = 139586270;

			clock_t start__           = clock();
			int received__            = WinterAndShopping().getNumber(vector <int>(first, first + (sizeof first / sizeof first[0])), vector <int>(red, red + (sizeof red / sizeof red[0])), vector <int>(green, green + (sizeof green / sizeof green[0])), vector <int>(blue, blue + (sizeof blue / sizeof blue[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}

		// custom cases

      case 9: {
			int first[]               = {1, 1, 31, 31};
			int red[]                 = {10, 10, 10, 10};
			int green[]               = {10, 10, 10, 10};
			int blue[]                = {10, 10, 10, 10};
			int expected__            = 366591467;

			clock_t start__           = clock();
			int received__            = WinterAndShopping().getNumber(vector <int>(first, first + (sizeof first / sizeof first[0])), vector <int>(red, red + (sizeof red / sizeof red[0])), vector <int>(green, green + (sizeof green / sizeof green[0])), vector <int>(blue, blue + (sizeof blue / sizeof blue[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
/*      case 10: {
			int first[]               = ;
			int red[]                 = ;
			int green[]               = ;
			int blue[]                = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = WinterAndShopping().getNumber(vector <int>(first, first + (sizeof first / sizeof first[0])), vector <int>(red, red + (sizeof red / sizeof red[0])), vector <int>(green, green + (sizeof green / sizeof green[0])), vector <int>(blue, blue + (sizeof blue / sizeof blue[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}*/
/*      case 11: {
			int first[]               = ;
			int red[]                 = ;
			int green[]               = ;
			int blue[]                = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = WinterAndShopping().getNumber(vector <int>(first, first + (sizeof first / sizeof first[0])), vector <int>(red, red + (sizeof red / sizeof red[0])), vector <int>(green, green + (sizeof green / sizeof green[0])), vector <int>(blue, blue + (sizeof blue / sizeof blue[0])));
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
