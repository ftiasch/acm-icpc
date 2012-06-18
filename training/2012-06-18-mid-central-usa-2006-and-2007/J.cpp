// Problem J -- Electronic Document Security
#include <cstdio>
#include <cstring>
#include <string>
#include <vector>
#include <utility>
using namespace std;

bool has[111][111];
bool visit[111];

int main() {
    int test_count = 0;
    while (true) {
        char buffer[222];
        scanf("%s", buffer);
        if (*buffer == '#') {
            break;
        }
        int length = strlen(buffer);
        memset(has, 0, sizeof(has));
        for (int lower = 0; lower < length; ++ lower) {
            int upper = lower;
            while (upper < length && buffer[upper] != ',') {
                upper ++;
            }
            int k = lower;
            while (buffer[k] != '+' && buffer[k] != '-' && buffer[k] != '=') {
                k ++;
            }
            if (buffer[k] == '=') {
                for (int i = lower; i < k; ++ i) {
                    for (int j = 0; j < 26; ++ j) {
                        has[buffer[i] - 'A'][j] = false;
                    }
                    for (int j = k + 1; j < upper; ++ j) {
                        has[buffer[i] - 'A'][buffer[j] - 'a'] = true;
                    }
                }
            } else {
                for (int i = lower; i < k; ++ i) {
                    for (int j = k + 1; j < upper; ++ j) {
                        if (buffer[k] == '+') {
//printf("%d, %d\n", buffer[i] - 'A', buffer[j] - 'a');
                            has[buffer[i] - 'A'][buffer[j] - 'a'] = true;
                        } else {
                            has[buffer[i] - 'A'][buffer[j] - 'a'] = false;
                        }
                    }
                }
            }
            lower = upper;
        }
        printf("%d:", ++ test_count);
        vector <pair <char, string> > result;
        for (int i = 0; i < 26; ++ i) {
            string acl = "";
            for (int j = 0; j < 26; ++ j) {
                if (has[i][j]) {
                    acl += j + 'a';
                }
            }
            if (acl != "") {
                result.push_back(make_pair(i + 'A', acl));
            }
        }
        for (int i = 0; i < (int)result.size(); ++ i) {
            int j = i;
            while (j < (int)result.size() && result[i].second == result[j].second) {
                j ++;
            }
            for (int k = i; k < j; ++ k) {
                printf("%c", result[k].first);
            }
            printf("%s", result[i].second.c_str());
            i = j - 1;
        }
        puts("");
    }
    return 0;
}
