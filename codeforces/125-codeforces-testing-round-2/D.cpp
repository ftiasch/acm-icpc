// Codeforces Testing Round #2
// Problem D -- Two progressions
#include <cstdio>
#include <cstring>
#include <cassert>
#include <vector>
#include <algorithm>

const int N = 30000;

int n, a[N], group[N], backup[N];

bool check(int start, int delta) {
    memset(group, -1, sizeof(group));
    std::vector <int> first;
    long long now = start;
    for (int i = 0; i < n; ++ i) {
        if (a[i] == now) {
            group[i] = 0;
            first.push_back(i);
            now += delta;
        }
    }
    if ((int)first.size() == n) {
        group[0] = 1;
        return true;
    }
    memcpy(backup, group, sizeof(group));
    for (int t = 0; t < 2; ++ t) {
        if (t) {
            memcpy(group, backup, sizeof(backup));
            group[first.back()] = -1;
        }
        std::vector <int> lefts;
        for (int i = n - 1; i >= 0; -- i) {
            if (group[i] == -1) {
                lefts.push_back(i);
            }
        }
        int new_start = a[lefts[0]];
        int new_delta = lefts.size() >= 2 ? a[lefts[1]] - a[lefts[0]] : 0;
        now = new_start;
        for (int i = n - 1; i >= 0; -- i) {
            if (a[i] == now && group[i] == -1) {
                group[i] = 1;
                now += new_delta;
            }
        }
        bool valid = true;
        for (int i = 0; i < n; ++ i) {
            valid &= group[i] != -1;
        }
        if (valid) {
            return true;
        }
    }
    return false;
}

bool solve() {
    for (int i = 0; i < n && i < 3; ++ i) {
        for (int j = i + 1; j < n && j < 3; ++ j) {
            if (check(a[i], a[j] - a[i])) {
                return true;
            }
        }
    }
    return false;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    if (solve()) {
        for (int t = 0; t < 2; ++ t) {
            bool first = true;
            for (int i = 0; i < n; ++ i) {
                if (group[i] == t) {
                    if (first) {
                        first = false;
                    } else {
                        putchar(' ');
                    }
                    printf("%d", a[i]);
                }
            }
            puts("");
        }
    } else {
        puts("No solution");
    }
    return 0;
}
