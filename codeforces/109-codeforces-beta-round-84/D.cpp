// Codeforces Beta Round #84
// Problem D -- Lucky Sorting
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 100000;

int n, a[N], to[N], order[N];
bool visit[N];

bool by_value(int i, int j) {
    if (a[i] != a[j]) {
        return a[i] < a[j];
    }
    return i < j;
}

bool lucky(int n) {
    while (n) {
        if (n % 10 != 4 && n % 10 != 7) {
            return false;
        }
        n /= 10;
    }
    return true;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    bool ordered = true;
    for (int i = 1; i < n; ++ i) {
        ordered &= a[i - 1] <= a[i];
    }
    if (ordered) {
        puts("0");
    } else {
        for (int i = 0; i < n; ++ i) {
            order[i] = i;
        }
        std::sort(order, order + n, by_value);
        for (int i = 0; i < n; ++ i) {
            to[order[i]] = i;
        }
        int start = 0;
        while (start < n && !lucky(a[start])) {
            start ++;
        }
        if (start >= n) {
            puts("-1");
        } else {
            memset(visit, 0, sizeof(visit));
            std::vector <std::pair <int, int> > answer;
#define APPLY(i, j) if (i != j) answer.push_back(std::make_pair(i, j))
            visit[start] = true;
            for (int i = start; order[i] != start; i = order[i]) {
                visit[order[i]] = true;
                APPLY(i, order[i]);
            }
            start = to[start];
            for (int i = 0; i < n; ++ i) {
                if (!visit[i]) {
                    visit[i] = true;
                    APPLY(start, i);
                    for (int j = i; order[j] != i; j = order[j]) {
                        visit[order[j]] = true;
                        APPLY(j, order[j]);
                    }
                    APPLY(start, to[i]);
                }
            }
            printf("%d\n", (int)answer.size());
            foreach (iter, answer) {
                printf("%d %d\n", iter->first + 1, iter->second + 1);
            }
        }
    }
    return 0;
}
