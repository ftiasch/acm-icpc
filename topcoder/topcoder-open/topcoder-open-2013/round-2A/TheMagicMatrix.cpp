#include <cmath>
#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <cassert>
#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <queue>
#include <list>
#include <deque>
#include <utility>
#include <set>
#include <map>

#define ALL(v) (v).begin(), (v).end()
#define SIZE(v) (int)((v).size())
#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

using std::cerr;
using std::endl;
using std::vector;
using std::string;

const int MOD = 1234567891;
 
struct Solver {
    int mod;
    vector <vector <int> > matrix;
 
    Solver(int mod): mod(mod) {
    }
 
    int inverse(int a) {
        return a == 1 ? 1 : (mod - mod / a) * inverse(mod % a);
    }
 
    void add(vector <int> row) {
        matrix.push_back(row);
    }
 
    int rank() {
        int n = matrix.size();
        if (n == 0) {
            return 0;
        }
        int m = matrix[0].size();
        int rank = 0;
        for (int j = 0; j < m - 1; ++ j) {
            int i = rank;
            while (i < n && matrix[i][j] == 0) {
                i ++;
            }
            if (i == n) {
                continue;
            }
            swap(matrix[rank], matrix[i]);
            for (int i = 0; i < n; ++ i) {
                if (i != rank && matrix[i][j] != 0) {
                    int times = matrix[i][j] * inverse(matrix[rank][j]) % mod;
                    for (int k = 0; k < m; ++ k) {
                        (matrix[i][k] += mod - times * matrix[rank][k] % mod) %= mod;
                    }
                }
            }
            rank ++;
        }
        for (int i = 0; i < n; ++ i) {
            if (matrix[i][m - 1] != 0) {
                int j = 0;
                while (j < m - 1 && matrix[i][j] == 0) {
                    j ++;
                }
                if (j == m - 1) {
                    return -1;
                }
            }
        }
        return rank;
    }
};
 
struct TheMagicMatrix {
    int pow(int a, int n) {
        int ret = 1;
        assert(n >= 0);
        while (n) {
            if (n & 1) {
                ret = (long long)ret * a % MOD;
            }
            a = (long long)a * a % MOD;
            n >>= 1;
        }
        return ret;
    }
 
    int m, numbers[10][10];
 
    int find(int n, vector <int> rows, vector <int> columns, vector <int> values) {
        m = rows.size();
        if (m < n) {
            return pow(10, (n - 1) * (n - 1) - m + 1);
        }
        // n <= 10
        if (n == 1) {
            return m == 0 ? 10 : 1;
        }
        memset(numbers, -1, sizeof(numbers));
        for (int i = 0; i < m; ++ i) {
            numbers[rows[i]][columns[i]] = values[i];
        }
        int answer = 1;
        int mods[2] = {2, 5};
#define get_id(i, j) ((i) * n + (j))
        for (int k = 0; k < 2; ++ k) {
            int mod = mods[k];
            Solver *solver = new Solver(mod);
            int vars = 1;
            for (int i = 0; i < n; ++ i) {
                vector <int> row(n * n + 2);
                for (int j = 0; j < n; ++ j) {
                    if (numbers[i][j] == -1) {
                        vars ++;
                        row[get_id(i, j)] = 1;
                    } else {
                        (row[n * n + 1] += numbers[i][j]) %= mod;
                    }
                }
                row[n * n] = 1;
                solver->add(row);
            }
            for (int j = 0; j < n; ++ j) {
                vector <int> row(n * n + 2);
                for (int i = 0; i < n; ++ i) {
                    if (numbers[i][j] == -1) {
                        row[get_id(i, j)] = 1;
                    } else {
                        (row[n * n + 1] += numbers[i][j]) %= mod;
                    }
                }
                row[n * n] = 1;
                solver->add(row);
            }
            int ret = solver->rank();
            if (ret == -1) {
                return 0;
            }
            answer = (long long)answer * pow(mod, vars - ret) % MOD;
        }
#undef get_id
        return answer;
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
			int n                     = 2;
			int rows[]                = {0, 1};
			int columns[]             = {1, 0};
			int values[]              = {4, 4};
			int expected__            = 10;

			clock_t start__           = clock();
			int received__            = TheMagicMatrix().find(n, vector <int>(rows, rows + (sizeof rows / sizeof rows[0])), vector <int>(columns, columns + (sizeof columns / sizeof columns[0])), vector <int>(values, values + (sizeof values / sizeof values[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 1: {
			int n                     = 2;
			int rows[]                = {0, 1};
			int columns[]             = {1, 0};
			int values[]              = {4, 7};
			int expected__            = 0;

			clock_t start__           = clock();
			int received__            = TheMagicMatrix().find(n, vector <int>(rows, rows + (sizeof rows / sizeof rows[0])), vector <int>(columns, columns + (sizeof columns / sizeof columns[0])), vector <int>(values, values + (sizeof values / sizeof values[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 2: {
			int n                     = 4;
			int rows[]                = {0, 0, 0, 1, 2, 2, 2, 3, 3, 3};
			int columns[]             = {0, 1, 2, 3, 0, 1, 2, 0, 1, 2};
			int values[]              = {3, 5, 1, 9, 5, 1, 8, 6, 7, 1};
			int expected__            = 2;

			clock_t start__           = clock();
			int received__            = TheMagicMatrix().find(n, vector <int>(rows, rows + (sizeof rows / sizeof rows[0])), vector <int>(columns, columns + (sizeof columns / sizeof columns[0])), vector <int>(values, values + (sizeof values / sizeof values[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 3: {
			int n                     = 474;
			int rows[]                = {44, 77};
			int columns[]             = {47, 74};
			int values[]              = {4, 7};
			int expected__            = 83494518;

			clock_t start__           = clock();
			int received__            = TheMagicMatrix().find(n, vector <int>(rows, rows + (sizeof rows / sizeof rows[0])), vector <int>(columns, columns + (sizeof columns / sizeof columns[0])), vector <int>(values, values + (sizeof values / sizeof values[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}

		// custom cases

/*      case 4: {
			int n                     = ;
			int rows[]                = ;
			int columns[]             = ;
			int values[]              = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = TheMagicMatrix().find(n, vector <int>(rows, rows + (sizeof rows / sizeof rows[0])), vector <int>(columns, columns + (sizeof columns / sizeof columns[0])), vector <int>(values, values + (sizeof values / sizeof values[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}*/
/*      case 5: {
			int n                     = ;
			int rows[]                = ;
			int columns[]             = ;
			int values[]              = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = TheMagicMatrix().find(n, vector <int>(rows, rows + (sizeof rows / sizeof rows[0])), vector <int>(columns, columns + (sizeof columns / sizeof columns[0])), vector <int>(values, values + (sizeof values / sizeof values[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}*/
/*      case 6: {
			int n                     = ;
			int rows[]                = ;
			int columns[]             = ;
			int values[]              = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = TheMagicMatrix().find(n, vector <int>(rows, rows + (sizeof rows / sizeof rows[0])), vector <int>(columns, columns + (sizeof columns / sizeof columns[0])), vector <int>(values, values + (sizeof values / sizeof values[0])));
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
