#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int M = 10000;

int n, m, s, k;
char line[M];

int main() {
    scanf("%d%d%d%d%s", &n, &m, &s, &k, line);
    bool hasStar = false;
    for (int j = 0; j < m; ++ j) {
        hasStar |= line[j] == '*';
    }
    if (hasStar) {
        int begin = 0;
        while (line[begin] != '*') {
            begin ++;
        }
        int end = begin;
        while (end < m && line[end] == '*') {
            end ++;
        }
        bool found = end - begin != s;
        for (int i = end; i < m; ++ i) {
            found |= line[i] == '*';
        }
        if (found) {
            puts("Impossible");
        } else if (s == 1 || s == n || k == 1 || k == n) {
            puts("Unique");
        } else {
            puts("Ambiguous");
        }
    } else {
        int wayCount = 0;
        wayCount += max(k - 1 - s + 1, 0) * max(m - s + 1, 0);
        wayCount += max(n - k - s + 1, 0) * max(m - s + 1, 0);
        if (wayCount == 0) {
            puts("Impossible");
        } else if (wayCount == 1) {
            puts("Unique");
        } else {
            puts("Ambiguous");
        }
    }
    return 0;
}
