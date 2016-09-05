#include <algorithm>
#include <bitset>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <functional>
#include <string>
#include <utility>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

using std::cerr;
using std::endl;
using std::string;
using std::vector;

struct Clicounting
{
    void update(int &x, int a)
    {
        x = std::max(x, a);
    }

    int count(std::vector<std::string> graph)
    {
        int n = graph.size();
        int n2 = 0;
        std::vector<std::pair<bool, int>> order(n);
        for (int i = 0; i < n; ++ i) {
            order[i] = {true, i};
        }
        auto mark = [&](int u)
        {
            if (order[u].first) {
                n2 ++;
                order[u].first = false;
            }
        };
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < i; ++ j) {
                if (graph[i][j] == '?') {
                    mark(i);
                    mark(j);
                }
            }
        }
        std::sort(ALL(order));
        if (true) {
            std::vector<std::string> new_graph(n, std::string(n, '0'));
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    new_graph[i][j] = graph[order[i].second][order[j].second];
                }
            }
            graph.swap(new_graph);
        }
        n2 = std::max(n2, n >> 1);
        std::vector<std::pair<int, int>> edges;
        for (int i = 0; i < n2; ++ i) {
            for (int j = 0; j < i; ++ j) {
                if (graph[i][j] == '?') {
                    edges.emplace_back(i, j);
                }
            }
        }
        int e = edges.size();
        std::vector<long long> neighbour(n);
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if (i == j || graph[i][j] != '0') {
                    neighbour[i] |= 1LL << j;
                }
            }
        }
        std::vector<int> max_clique(1 << n - n2);
        for (int mask = 1; mask < 1 << n - n2; ++ mask) {
            int i = __builtin_ctz(mask);
            int new_mask = mask ^ (1 << i);
            max_clique.at(mask) = std::max(max_clique.at(new_mask), max_clique.at(new_mask & neighbour.at(n2 + i) >> n2) + 1);
        }
        std::vector<int> result(1 << e);
        for (int mask = 0; mask < 1 << n2; ++ mask) {
            bool valid = true;
            long long nb = (1LL << n) - 1;
            for (int i = 0; i < n2; ++ i) {
                if (mask >> i & 1) {
                    int half = neighbour[i] & (1 << n2) - 1;
                    valid &= (mask & half) == mask;
                    nb &= neighbour[i];
                }
            }
            if (valid) {
                int edge_mask = 0;
                for (int i = 0; i < e; ++ i) {
                    edge_mask |= ((~mask >> edges[i].first & 1) || (~mask >> edges[i].second & 1)) << i;
                }
                update(result[edge_mask], __builtin_popcount(mask) + max_clique[nb >> n2]);
            }
        }
        int result_ = 0;
        for (int mask = (1 << e) - 1; mask >= 0; -- mask) {
            result_ += result[mask];
            for (int i = 0; i < e; ++ i) {
                if (mask >> i & 1) {
                    update(result[mask ^ (1 << i)], result[mask]);
                }
            }
        }
        return result_;
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
            string g[]                = {"011","101","110"};
            int expected__            = 3;

            clock_t start__           = clock();
            int received__            = Clicounting().count(vector <string>(g, g + (sizeof g / sizeof g[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            string g[]                = {"01?","101","?10"};
            int expected__            = 5;

            clock_t start__           = clock();
            int received__            = Clicounting().count(vector <string>(g, g + (sizeof g / sizeof g[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            string g[]                = {"0?","?0"};
            int expected__            = 3;

            clock_t start__           = clock();
            int received__            = Clicounting().count(vector <string>(g, g + (sizeof g / sizeof g[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            string g[]                = {"0??","?0?","??0"};
            int expected__            = 16;

            clock_t start__           = clock();
            int received__            = Clicounting().count(vector <string>(g, g + (sizeof g / sizeof g[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            string g[]                = {"0???","?0??","??0?","???0"};
            int expected__            = 151;

            clock_t start__           = clock();
            int received__            = Clicounting().count(vector <string>(g, g + (sizeof g / sizeof g[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

        case 5: {
            string g[]                = {"00110011011101011110101111011111101111", "001111111110111100111010011111001?1100", "11011110101101101110100111110111110011", "11100101011010101110111111111100100111", "01100001111111111111110111111111111100", "01110010011111101111100001101011011101", "11100101111101101100111001011101111010", "11011010111111101001111101010001111110", "01101011010111111111110101110111101101", "11011111101111001111111111101110101101", "11111111010101101110110010111110011011", "10101111111011010101111110010001110111", "01011101110101011111001010110100011011", "11101111111110111101101011110110111011", "01111111101001011110101001111010111111", "11001000100111100001100011110101110001", "10111111111011100110101111110111101101", "10111110111111101011101101100001111110", "11111100111010101101011100111101110111", "01001101110111010110111110101111101100", "11111111111101111101000010110001110001", "00011011111100000011000101110111011000", "11010011010111101111000010011111111101", "10111001110100001111010011000011111100", "10111000011111011001101100111010010101", "11111111110001111100010100011011111011", "01111100111011111111110010001101111110", "11111011101111111010111011000110101111", "11011110011000100011001011100111101110", "11111010111011011011011000111011110111", "10101100111001101001011111011101101111", "10101111100100011111111101101110111110", "11111011110101111111101101111111011110", "0?101111001111110110111111100101101110", "11001111111011101101011101111011110000", "11011101110100101111001110111111110010", "10110011001111100110000001111111110100", "10110100111111111010101011010110000000"};
            int expected__            = 20;

            clock_t start__           = clock();
            int received__            = Clicounting().count(vector <string>(g, g + (sizeof g / sizeof g[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
/*      case 6: {
            string g[]                = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = Clicounting().count(vector <string>(g, g + (sizeof g / sizeof g[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            string g[]                = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = Clicounting().count(vector <string>(g, g + (sizeof g / sizeof g[0])));
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
