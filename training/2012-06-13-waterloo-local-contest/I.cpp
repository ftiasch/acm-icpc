#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <string>
#include <set>
#include <algorithm>
using namespace std;

typedef unsigned long long Hash;

const int N = 111;
const int M = 1111;
const Hash MAGIC = 173;

int n, length[N];
char text[N][M];
Hash hash[N][M], power[M];
vector <pair <Hash, int> > values;
set <Hash> choice;
vector <string> result;

bool check(bool store, int limit) {
    values.clear();
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j + limit <= length[i]; ++ j) {
            values.push_back(make_pair(hash[i][j] - hash[i][j + limit] * power[limit], i));
        }
    }
    sort(values.begin(), values.end());
//printf("%d\n", limit);
//for (int i = 0; i < (int)values.size(); ++ i) {
//    printf("%llu %d\n", values[i].first, values[i].second);
//}
    for (int lower = 0; lower < (int)values.size(); ++ lower) {
        int upper = lower + 1;
        while (upper < (int)values.size() && values[lower].first == values[upper].first) {
            upper ++;
        }
        int type = 1;
        for (int i = lower + 1; i < upper; ++ i) {
            type += (values[i - 1].second != values[i].second);
        }
        if (type * 2 > n) {
            if (!store) {
                return true;
            }
            choice.insert(values[lower].first);
        }
        lower = upper - 1;
    }
    return false;
}

int main() {
    power[0] = 1;
    for (int j = 1; j < M; ++ j) {
        power[j] = power[j - 1] * MAGIC;
    }
    int test_count = 0;
    while (scanf("%d", &n) == 1 && n > 0) {
        test_count ++;
        if (test_count > 1) {
            puts("");
        }
        for (int i = 0; i < n; ++ i) {
            scanf("%s", text[i]);
            length[i] = strlen(text[i]);
            hash[i][length[i]] = 0;
            for (int j = length[i] - 1; j >= 0; -- j) {
                hash[i][j] = hash[i][j + 1] * MAGIC + text[i][j] - 'a' + 1;
            }
        }
        int lower = 0;
        int upper = M;
        while (lower < upper) {
            int mider = (lower + upper + 1) >> 1;
            bool ret = check(false, mider);
            if (ret) {
                lower = mider;
            } else {
                upper = mider - 1;
            }
        }
        //printf("-- %d\n", lower);
        if (lower == 0) {
            puts("?");
        } else {
            choice.clear();
            check(true, lower);
            result.clear();
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j + lower <= length[i]; ++ j) {
                    Hash ret = hash[i][j] - hash[i][j + lower] * power[lower];
                    if (choice.find(ret) != choice.end()) {
                        choice.erase(ret);
                        result.push_back(string(text[i] + j, lower));
                    }
                }
            }
            sort(result.begin(), result.end());
            for (int i = 0; i < (int)result.size(); ++ i) {
                printf("%s\n", result[i].c_str());
            }
        }
    }
    return 0;
}
